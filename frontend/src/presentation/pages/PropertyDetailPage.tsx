import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

import {
  Box,
  Typography,
  Button,
  Card,
  CardContent,
  Grid,
  Chip,
  Divider,
  Stack,
  IconButton
} from '@mui/material';

import {
  ArrowBack,
  Favorite,
  FavoriteBorder,
  Edit,
  Delete
} from '@mui/icons-material';
import { toast } from 'react-toastify';
import { useAuth } from '../../contexts/AuthContext';
import { useFavorites } from '../../contexts/FavoritesContext';
import { Configuration, PropertyControllerApi } from '../../infrastructure/apis/client';
import { AppRoute } from '../../routes';

enum PropertyType {
  APARTMENT = 'APARTMENT',
  HOUSE = 'HOUSE'
}

type Property = {
  id?: string;
  title?: string;
  description?: string;
  address?: string;
  rooms?: number;
  bathrooms?: number;
  usableArea?: number;
  constructionYear?: number;
  price?: number;
  photoUrls?: string[];
  sellerId?: string;
  propertyType: PropertyType;
  createdAt?: Date;
  lastUpdated?: Date;

  comfort?: number;
  floor?: number;
  layout?: string;

  floors?: number;
  landArea?: number;
  houseType?: string;
};

const authConfig = new Configuration({basePath: import.meta.env.VITE_APP_API_BASE_URL, accessToken: async () => {return localStorage.getItem('authToken') || '';},});
const propertyApi = new PropertyControllerApi(authConfig);

function mapToProperty(dto: any): Property {
  if (dto.propertyType === PropertyType.APARTMENT) {
    return { ...dto, propertyType: PropertyType.APARTMENT };
  }

  if (dto.propertyType === PropertyType.HOUSE) {
    return { ...dto, propertyType: PropertyType.HOUSE };
  }

  throw new Error('Invalid property type');
}

async function getProperty(id: string): Promise<Property> {
  const dto = await propertyApi.getById1({ id });
  return mapToProperty(dto);
}

export function PropertyDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const { isFavorite, addFavorite, removeFavorite } = useFavorites();
  const { isAuthenticated, user } = useAuth();

  const [property, setProperty] = useState<Property | null>(null);
  const [imageIndex, setImageIndex] = useState(0);
  const [isFavoriteLocal, setIsFavoriteLocal] = useState(false);

  useEffect(() => {
    if (id)
      load();
  }, [id, user]);

  const load = async () => {
    try {
      const data = await getProperty(id || '');
      console.log(user?.id);
      setProperty(data);
    } catch {
      toast.error('Failed to load property');
    }
  };

  useEffect(() => {
    const check = async () => {
      const result = await isFavorite(property?.id || '');
      setIsFavoriteLocal(result);
    };

    check();
  }, [property]);

  const handleToggleFavorite = async () => {
    if (!property)
      return;

    try {
      if (isFavoriteLocal) {
        await removeFavorite(property.id || '' );
        setIsFavoriteLocal(false);
      }
      else {
        await addFavorite(property.id || '' );
        setIsFavoriteLocal(true);
      }
    } catch {
    }
  };

  const handleDelete = async () => {
    if (!property)
      return;

    try {
      console.log(user?.id);
      console.log(property.sellerId );
      console.log(property.id );
      await propertyApi._delete({ id: property.id || '' });
      toast.success('Deleted successfully');
      navigate(`${AppRoute.Properties}`);
    } catch {
      toast.error('Delete failed');
    }
  };

  const formatPrice = (price?: number) => price ? new Intl.NumberFormat('en-US', {style: 'currency', currency: 'EUR', maximumFractionDigits: 0}).format(price) : '-';
  const handlePrevImage = () => {
    const photos = property?.photoUrls;

    if (!photos || photos.length === 0)
      return;

    setImageIndex((prev) =>
      prev === 0 ? photos.length - 1 : prev - 1
    );
  };

  const handleNextImage = () => {
    const photos = property?.photoUrls;

    if (!photos || photos.length === 0)
      return;

    setImageIndex((prev) =>
      prev === photos.length - 1 ? 0 : prev + 1
    );
  };

  if (!property) {
    return (
      <Box sx={{ p: 4, textAlign: 'center' }}>
        <Typography variant="h5">Loading...</Typography>
      </Box>
    );
  }

  return (
    <Box sx={{ p: 4, bgcolor: '#f5f5f5', minHeight: '100vh' }}>
      <Button
        startIcon={<ArrowBack />}
        onClick={() => navigate(AppRoute.Properties)}
        sx={{ mb: 2 }}
      >
        Back
      </Button>

      <Grid container spacing={4}>
        <Grid size={{ xs: 12, md: 8 }}>
          <Card sx={{ borderRadius: 3 }}>
            <CardContent sx={{ p: 4 }}>
              <Stack spacing={2}>
                <Stack direction="row" sx={{justifyContent: "space-between", alignItems: "flex-start"}}>
                  <Box>
                    <Chip label={property.propertyType} sx={{ mb: 1 }} />

                    <Typography variant="h4">
                      {property.title}
                    </Typography>

                    <Typography color="text.secondary">
                      {property.address}
                    </Typography>
                  </Box>

                  { isAuthenticated &&
                    <Stack direction="row">
                      
                      <IconButton onClick={handleToggleFavorite}>
                        { isFavoriteLocal ? (
                          <Favorite color="error" />
                        ) : (
                          <FavoriteBorder />
                        )}
                      </IconButton>

                      <IconButton onClick={() => navigate(`/properties/edit/${property.id}`)}>
                        <Edit />
                      </IconButton>

                      { property.sellerId === user?.id && 
                      <IconButton color="error" onClick={handleDelete}>
                        <Delete />
                      </IconButton>
                      }
                    </Stack>
                    }
                </Stack>

                <Box sx={{ display: 'flex', justifyContent: 'center' }}>
                  <Box
                    sx={{
                      position: 'relative',
                      width: '100%',
                      borderRadius: 2,
                      overflow: 'hidden'
                    }}
                  >
                    <Box
                      component="img"
                      src={property.photoUrls?.[imageIndex] || ''}
                      sx={{
                        width: '75%',
                        height: 360,
                        objectFit: 'cover',
                        borderRadius: 2
                      }}
                    />

                    <IconButton
                      onClick={handlePrevImage}
                      sx={{
                        position: 'absolute',
                        top: '50%',
                        left: 10,
                        transform: 'translateY(-50%)',
                        bgcolor: '#000000AA',
                        color: 'white'
                      }}
                    >
                      ‹
                    </IconButton>

                    <IconButton
                      onClick={handleNextImage}
                      sx={{
                        position: 'absolute',
                        top: '50%',
                        right: 10,
                        transform: 'translateY(-50%)',
                        bgcolor: '#000000AA',
                        color: 'white'
                      }}
                    >
                      ›
                    </IconButton>

                    <Box
                      sx={{
                        position: 'absolute',
                        bottom: 10,
                        right: 10,
                        bgcolor: 'rgba(0,0,0,0.5)',
                        color: 'white',
                        px: 1.5,
                        py: 0.5,
                        borderRadius: 1,
                        fontSize: 12
                      }}
                    >
                      {imageIndex + 1} / {property.photoUrls?.length || 0}
                    </Box>
                  </Box>
                </Box>
              </Stack>

              <Typography
                variant="h3"
                sx={{ mt: 2, color: 'primary.main', fontWeight: 700 }}
              >
                {formatPrice(property.price)}
              </Typography>

              <Divider sx={{ my: 3 }} />

              <Grid container spacing={2}>
                {[
                  { label: 'Rooms', value: property.rooms },
                  { label: 'Bathrooms', value: property.bathrooms },
                  { label: 'Area', value: `${property.usableArea} m²` },
                  { label: 'Year', value: property.constructionYear }
                ].map((item, i) => (
                  <Grid key={i} size={{ xs: 6, md: 3 }}>
                    <Card sx={{ p: 2, textAlign: 'center', borderRadius: 2 }}>
                      <Typography variant="h6">
                        {item.value ?? '-'}
                      </Typography>
                      <Typography variant="body2" color="text.secondary">
                        {item.label}
                      </Typography>
                    </Card>
                  </Grid>
                ))}
              </Grid>

              <Divider sx={{ my: 3 }} />

              <Card sx={{ mb: 3 }}>
                <CardContent>
                  <Typography variant="h6">
                    Description
                  </Typography>
                  <Typography color="text.secondary">
                    {property.description || 'No description'}
                  </Typography>
                </CardContent>
              </Card>

              <Card sx={{ mb: 3 }}>
                <CardContent>
                  <Typography variant="h6">
                    Details
                  </Typography>

                  <Grid container spacing={2}>
                    <Grid size={{ xs: 12, sm: 6 }}>
                      <Card sx={{ p: 2 }}>
                        <Typography variant="body2" color="text.secondary">
                          Price / m²
                        </Typography>
                        <Typography>
                          {formatPrice(
                            property.price! / property.usableArea!
                          )}
                        </Typography>
                      </Card>
                    </Grid>

                    <Grid size={{ xs: 12, sm: 6 }}>
                      <Card sx={{ p: 2 }}>
                        <Typography variant="body2" color="text.secondary">
                          Address
                        </Typography>
                        <Typography>
                          {property.address}
                        </Typography>
                      </Card>
                    </Grid>
                  </Grid>
                </CardContent>
              </Card>

              {property.propertyType === PropertyType.APARTMENT && (
                <Card>
                  <CardContent>
                    <Typography variant="h6">
                      Apartment Details
                    </Typography>

                    <Grid container spacing={2}>
                      <Grid size={{ xs: 12, sm: 4 }}>
                        <Card sx={{ p: 2 }}>Comfort: {property.comfort}</Card>
                      </Grid>
                      <Grid size={{ xs: 12, sm: 4 }}>
                        <Card sx={{ p: 2 }}>Floor: {property.floor}</Card>
                      </Grid>
                      <Grid size={{ xs: 12, sm: 4 }}>
                        <Card sx={{ p: 2 }}>Layout: {property.layout}</Card>
                      </Grid>
                    </Grid>
                  </CardContent>
                </Card>
              )}

              {property.propertyType === PropertyType.HOUSE && (
                <Card>
                  <CardContent>
                    <Typography variant="h6">
                      House Details
                    </Typography>

                    <Grid container spacing={2}>
                      <Grid size={{ xs: 12, sm: 4 }}>
                        <Card sx={{ p: 2 }}>Floors: {property.floors}</Card>
                      </Grid>
                      <Grid size={{ xs: 12, sm: 4 }}>
                        <Card sx={{ p: 2 }}>
                          Land: {property.landArea} m²
                        </Card>
                      </Grid>
                      <Grid size={{ xs: 12, sm: 4 }}>
                        <Card sx={{ p: 2 }}>Type: {property.houseType}</Card>
                      </Grid>
                    </Grid>
                  </CardContent>
                </Card>
              )}
            </CardContent>
          </Card>
        </Grid>

        <Grid size={{ xs: 12, md: 4 }}>
          <Card sx={{ borderRadius: 3, position: 'sticky', top: 24 }}>
            <CardContent>
              <Typography variant="h6">
                Contact
              </Typography>

              <Card sx={{ p: 2, mb: 2 }}>
                Seller ID: {property.sellerId}
              </Card>

              <Button
                fullWidth
                variant="contained"
                sx={{ mb: 1 }}
                disabled={!isAuthenticated}
              >
                Show Phone
              </Button>

              <Button fullWidth variant="outlined" disabled={!isAuthenticated}>
                Send Email
              </Button>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
}