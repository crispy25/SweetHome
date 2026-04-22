import { useEffect, useState } from 'react';
import { Box, Card, CardContent, TextField, Button, Typography, Grid, MenuItem, Divider, Stack } from '@mui/material';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import { PropertyControllerApi, ApartmentControllerApi, HouseControllerApi, Configuration } from '../../infrastructure/apis/client';
import { PropertyType } from '../../types/PropertyTypes';
import ImageUploader from '../components/ImageUploader';

const authConfig = new Configuration({basePath: import.meta.env.VITE_APP_API_BASE_URL, accessToken: async () => {return localStorage.getItem('authToken') || '';},});
const propertyApi = new PropertyControllerApi(authConfig);
const apartmentApi = new ApartmentControllerApi(authConfig);
const houseApi = new HouseControllerApi(authConfig);


type FormState = {
  title: string;
  description: string;
  address: string;
  rooms: number;
  bathrooms: number;
  usableArea: number;
  constructionYear: number;
  price: number;
  photoUrls: string[];
  propertyType: PropertyType;

  comfort?: number;
  floor?: number;
  layout?: string;

  floors?: number;
  landArea?: number;
  houseType?: string;
};

export default function PropertyFormPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const isEdit = Boolean(id);

  const [form, setForm] = useState<FormState>({
    title: '',
    description: '',
    address: '',
    rooms: 0,
    bathrooms: 0,
    usableArea: 0,
    constructionYear: new Date().getFullYear(),
    price: 0,
    photoUrls: [],
    propertyType: PropertyType.APARTMENT
  });

  useEffect(() => {
    if (!id) return;

    (async () => {
      try {
        const data = await propertyApi.getById1({ id: id });
        setForm(data as any);
      } catch {
        toast.error('Failed to load property');
      }
    })();
  }, [id]);


  const update = (key: keyof FormState, value: any) => {
    setForm((prev) => ({ ...prev, [key]: value }));
  };

 const handleSubmit = async () => {
  try {
    if (form.propertyType === PropertyType.APARTMENT) {
      const apartmentDto = {
        title: form.title,
        description: form.description,
        address: form.address,
        rooms: form.rooms,
        bathrooms: form.bathrooms,
        usableArea: form.usableArea,
        constructionYear: form.constructionYear,
        price: form.price,
        photoUrls: form.photoUrls,

        comfort: Number(form.comfort),
        floor: Number(form.floor),
        layout: form.layout as any
      };

      if (isEdit) {
        await apartmentApi.update1({
          id: id!,
          apartmentDto
        });
      } else {
        await apartmentApi.create1({
          apartmentDto
        });
      }
    } else {
      const houseDto = {
        title: form.title,
        description: form.description,
        address: form.address,
        rooms: form.rooms,
        bathrooms: form.bathrooms,
        usableArea: form.usableArea,
        constructionYear: form.constructionYear,
        price: form.price,
        photoUrls: form.photoUrls,

        floors: Number(form.floors),
        landArea: Number(form.landArea),
        houseType: form.houseType as any
      };
        console.log(houseDto);

      if (isEdit) {
        await houseApi.update({
          id: id!,
          houseDto
        });
      } else {
        await houseApi.create({
          houseDto
        });
      }
    }

    toast.success(isEdit ? 'Updated' : 'Created');
    navigate('/properties');
  } catch (e) {

    console.error(e);
    toast.error('Failed to save');
  }
};

  return (
    <Box sx={{ p: 4, bgcolor: '#f5f5f5', minHeight: '100vh' }}>
      <Card sx={{ maxWidth: 900, mx: 'auto', borderRadius: 3 }}>
        <CardContent sx={{ p: 4 }}>

          <Typography variant="h4">
            {isEdit ? 'Edit Property' : 'Create Property'}
          </Typography>

          <Grid container spacing={2}>

            <Grid size={{ xs: 12 }}>
              <TextField
                select
                fullWidth
                label="Property Type"
                value={form.propertyType}
                onChange={(e) =>
                  update('propertyType', e.target.value as PropertyType)
                }
              >
                <MenuItem value={PropertyType.APARTMENT}>
                  Apartment
                </MenuItem>
                <MenuItem value={PropertyType.HOUSE}>
                  House
                </MenuItem>
              </TextField>
            </Grid>

            <Grid size={{ xs: 12, sm: 6 }}>
              <TextField
                fullWidth
                label="Title"
                value={form.title}
                onChange={(e) => update('title', e.target.value)}
              />
            </Grid>

            <Grid size={{ xs: 12, sm: 6 }}>
              <TextField
                fullWidth
                label="Description"
                value={form.description}
                onChange={(e) => update('description', e.target.value)}
              />
            </Grid>

            <Grid size={{ xs: 12, sm: 6 }}>
              <TextField
                fullWidth
                label="Address"
                value={form.address}
                onChange={(e) => update('address', e.target.value)}
              />
            </Grid>

            <Grid size={{ xs: 12, sm: 4 }}>
              <TextField
                fullWidth
                label="Rooms"
                type="number"
                value={form.rooms}
                onChange={(e) => update('rooms', Number(e.target.value))}
              />
            </Grid>

            <Grid size={{ xs: 12, sm: 4 }}>
              <TextField
                fullWidth
                label="Bathrooms"
                type="number"
                value={form.bathrooms}
                onChange={(e) => update('bathrooms', Number(e.target.value))}
              />
            </Grid>

            <Grid size={{ xs: 12, sm: 4 }}>
              <TextField
                fullWidth
                label="Area (m²)"
                type="number"
                value={form.usableArea}
                onChange={(e) => update('usableArea', Number(e.target.value))}
              />
            </Grid>

            <Grid size={{ xs: 12, sm: 6 }}>
              <TextField
                fullWidth
                label="Price"
                type="number"
                value={form.price}
                onChange={(e) => update('price', Number(e.target.value))}
              />
            </Grid>

            <Grid size={{ xs: 12, sm: 6 }}>
              <TextField
                fullWidth
                label="Construction Year"
                type="number"
                value={form.constructionYear}
                onChange={(e) =>
                  update('constructionYear', Number(e.target.value))
                }
              />
            </Grid>

            <Grid size={{ xs: 12 }}>
              <Divider sx={{ my: 2 }} />
              <Typography>Images</Typography>

              <ImageUploader
                onChange={(urls) => update('photoUrls', urls)}
              />
            </Grid>

            {form.propertyType === PropertyType.APARTMENT && (
              <>
                <Grid size={{ xs: 12, sm: 4 }}>
                  <TextField
                    fullWidth
                    label="Comfort"
                    type="number"
                    value={form.comfort || ''}
                    onChange={(e) =>
                      update('comfort', Number(e.target.value))
                    }
                  />
                </Grid>

                <Grid size={{ xs: 12, sm: 4 }}>
                  <TextField
                    fullWidth
                    label="Floor"
                    type="number"
                    value={form.floor || ''}
                    onChange={(e) =>
                      update('floor', Number(e.target.value))
                    }
                  />
                </Grid>

                <Grid size={{ xs: 12, sm: 4 }}>
                  <TextField
                    fullWidth
                    label="Layout"
                    value={form.layout || ''}
                    onChange={(e) => update('layout', e.target.value)}
                  />
                </Grid>
              </>
            )}

            {form.propertyType === PropertyType.HOUSE && (
              <>
                <Grid size={{ xs: 12, sm: 4 }}>
                  <TextField
                    fullWidth
                    label="Floors"
                    type="number"
                    value={form.floors || ''}
                    onChange={(e) =>
                      update('floors', Number(e.target.value))
                    }
                  />
                </Grid>

                <Grid size={{ xs: 12, sm: 4 }}>
                  <TextField
                    fullWidth
                    label="Land Area"
                    type="number"
                    value={form.landArea || ''}
                    onChange={(e) =>
                      update('landArea', Number(e.target.value))
                    }
                  />
                </Grid>

                <Grid size={{ xs: 12, sm: 4 }}>
                  <TextField
                    fullWidth
                    label="House Type"
                    value={form.houseType || ''}
                    onChange={(e) => update('houseType', e.target.value)}
                  />
                </Grid>
              </>
            )}
            <Grid size={{ xs: 12 }}>
              <Stack direction="row" sx={{justifyContent:"flex-end"}}>
                <Button
                  variant="contained"
                  size="large"
                  onClick={handleSubmit}
                >
                  {isEdit ? 'Update' : 'Create'}
                </Button>
              </Stack>
            </Grid>

          </Grid>
        </CardContent>
      </Card>
    </Box>
  );
}