import { useEffect, useState, memo } from "react";
import { useNavigate } from "react-router-dom";
import { Configuration, PagePropertyResponseDto, PropertyControllerApi,} from "../../infrastructure/apis/client";
import { Grid, Card, TextField, Box, Typography, FormControl, InputLabel, Select, MenuItem, Button, Collapse, Divider, CircularProgress, CardMedia, CardContent, Pagination } from "@mui/material";
import { useDebounce } from "../../hooks/useDebounce";
import { AppRoute } from "../../routes";
import { SortBy, Order, PropertyType } from "../../types/PropertyTypes";


const authConfig = new Configuration({ basePath: import.meta.env.VITE_APP_API_BASE_URL, accessToken: async () => localStorage.getItem("authToken") || "",});
const api = new PropertyControllerApi(authConfig);

const NumberField = memo(
  ({
    label,
    field,
    value,
    onChange,
  }: {
    label: string;
    field: string;
    value: any;
    onChange: (field: string, value: any) => void;
  }) => {
    return (
      <Grid size={{ xs: 12, md: 3 }}>
        <Card sx={{ p: 2 }}>
          <TextField
            label={label}
            type="number"
            fullWidth
            value={value ?? ""}
            onChange={(e) => onChange(field, e.target.value)}
          />
        </Card>
      </Grid>
    );
  }
);

export default function PropertiesPage() {
  const navigate = useNavigate();

  const [draftFilters, setDraftFilters] = useState<any>({propertyType: PropertyType.APARTMENT,});
  const [page, setPage] = useState(0);
  const [size] = useState(6);
  const [sortBy, setSortBy] = useState<SortBy>(SortBy.CREATED_AT);
  const [order] = useState<Order>(Order.DESC);
  const [data, setData] = useState<PagePropertyResponseDto | null>(null);
  const [loading, setLoading] = useState(false);
  const [showAdvanced, setShowAdvanced] = useState(false);
  const debouncedFilters = useDebounce(draftFilters, 500);

  const propertyType: PropertyType = draftFilters.propertyType;

  const updateFilter = (key: string, value: any) => {
    setDraftFilters((prev: any) => ({
      ...prev,
      [key]: value === "" ? undefined : value,
    }));
    setPage(0);
  };

  const cleanFilters = (obj: any) => Object.fromEntries(Object.entries(obj).filter(([_, v]) => v !== undefined && v !== "") );

  const fetchProperties = async () => {
    setLoading(true);
    try {
      const response = await api.searchRaw({propertySearchDto: cleanFilters(debouncedFilters), page, size, sortBy, order});
      const json = (await response.raw.json()) as PagePropertyResponseDto;
      setData(json);
    } catch (err) {
      setData({});
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProperties();
  }, [debouncedFilters, page, sortBy, order]);

  const openProperty = (id: string) => {
    navigate(`${AppRoute.Properties}/${id}`);
  };

  const renderNumberField = (label: string, field: string) => (
    <NumberField
      label={label}
      field={field}
      value={draftFilters[field]}
      onChange={updateFilter}
    />
  );

  return (
    <Box sx={{ p: 3, backgroundColor: "#fff", minHeight: "100vh" }}>
      <Typography variant="h5" sx={{ mb: 2, fontWeight: 600 }}>
        Find Properties
      </Typography>

      <Grid container spacing={2} sx={{ mb: 2 }}>
        <Grid size={{ xs: 12, md: 3 }}>
          <Card sx={{ p: 2 }}>
            <FormControl fullWidth>
              <InputLabel>Property Type</InputLabel>
              <Select
                value={propertyType}
                label="Property Type"
                onChange={(e) => updateFilter("propertyType", e.target.value)}
              >
                <MenuItem value="HOUSE">House</MenuItem>
                <MenuItem value="APARTMENT">Apartment</MenuItem>
              </Select>
            </FormControl>
          </Card>
        </Grid>

        {renderNumberField("Min Price", "minPrice")}
        {renderNumberField("Max Price", "maxPrice")}

        <Grid size={{ xs: 12, md: 3 }}>
          <Card sx={{ p: 2 }}>
            <FormControl fullWidth>
              <InputLabel>Sort By</InputLabel>
              <Select
                value={sortBy}
                label="Sort By"
                onChange={(e) => setSortBy(e.target.value as SortBy)}
              >
                <MenuItem value="CREATED_AT">Newest</MenuItem>
                <MenuItem value="PRICE">Price</MenuItem>
                <MenuItem value="ROOMS">Rooms</MenuItem>
                <MenuItem value="USABLE_AREA">Area</MenuItem>
              </Select>
            </FormControl>
          </Card>
        </Grid>
      </Grid>

      <Box sx={{ mb: 2 }}>
        <Button
          size="small"
          variant="outlined"
          onClick={() => setShowAdvanced((v) => !v)}
        >
          {showAdvanced ? "Hide advanced filters" : "Show advanced filters"}
        </Button>
      </Box>

      <Collapse in={showAdvanced}>
        <Box sx={{ mb: 3 }}>
          <Divider sx={{ mb: 2 }} />

          <Grid container spacing={2}>
            {renderNumberField("Min Rooms", "minRooms")}
            {renderNumberField("Max Rooms", "maxRooms")}
            {renderNumberField("Min Bathrooms", "minBathrooms")}
            {renderNumberField("Max Bathrooms", "maxBathrooms")}
            {renderNumberField("Min Usable Area", "minUsableArea")}
            {renderNumberField("Max Usable Area", "maxUsableArea")}
            {renderNumberField("Min Construction Year", "minConstructionYear")}
            {renderNumberField("Max Construction Year", "maxConstructionYear")}
            {propertyType === "HOUSE" && (
              <>
                {renderNumberField("Min Floors", "minFloors")}
                {renderNumberField("Max Floors", "maxFloors")}
                {renderNumberField("Min Land Area", "minLandArea")}
                {renderNumberField("Max Land Area", "maxLandArea")}

                <Grid size={{ xs: 12, md: 3 }}>
                  <Card sx={{ p: 2 }}>
                    <TextField
                      label="House Type"
                      fullWidth
                      onChange={(e) =>
                        updateFilter("houseType", e.target.value)
                      }
                    />
                  </Card>
                </Grid>
              </>
            )}

            {propertyType === "APARTMENT" && (
              <>
                {renderNumberField("Min Floor", "minFloor")}
                {renderNumberField("Max Floor", "maxFloor")}
                {renderNumberField("Comfort", "comfort")}

                <Grid size={{ xs: 12, md: 3 }}>
                  <Card sx={{ p: 2 }}>
                    <TextField
                      label="Layout"
                      fullWidth
                      onChange={(e) => updateFilter("layout", e.target.value)}
                    />
                  </Card>
                </Grid>
              </>
            )}
          </Grid>
        </Box>
      </Collapse>

      {loading ? (
        <Box sx={{ display: "flex", justifyContent: "center", mt: 6 }}>
          <CircularProgress />
        </Box>
      ) : (
        <>
          <Grid container spacing={3}>
            {data?.content?.map((property: any) => (
              <Grid size={{ xs: 12, md: 4 }} key={property.id}>
                <Card
                  onClick={() => openProperty(property.id)}
                  sx={{
                    borderRadius: 3,
                    cursor: "pointer",
                    transition: "0.2s",
                    "&:hover": {
                      transform: "scale(1.02)",
                      boxShadow: 6,
                    },
                  }}
                >
                  <CardMedia
                    component="img"
                    height="200"
                    image={property.photoUrls?.[0] || "/"}
                  />

                  <CardContent>
                    <Typography variant="h6">{property.title}</Typography>
                    <Typography variant="body2" sx={{ color: "text.secondary" }}>
                      {property.address}
                    </Typography>
                    <Typography
                      variant="h6"
                      color="primary"
                      sx={{ mt: 1, fontWeight: 700 }}
                    >
                      €{property.price}
                    </Typography>
                    <Typography variant="body2" sx={{ mt: 1 }}>
                      {property.rooms} rooms - {property.usableArea} m²
                    </Typography>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>

          <Box sx={{ display: "flex", justifyContent: "center", mt: 4 }}>
            <Pagination
              count={data?.totalPages || 1}
              page={page + 1}
              onChange={(_, v) => setPage(v - 1)}
            />
          </Box>
        </>
      )}
    </Box>
  );
}
