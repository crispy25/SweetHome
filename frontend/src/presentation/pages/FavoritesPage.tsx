import { useEffect } from "react";

import DeleteIcon from "@mui/icons-material/Delete";
import { useNavigate } from "react-router-dom";
import { useFavorites } from "../../contexts/FavoritesContext";
import { AppRoute } from "../../routes";
import { Box, Typography, CircularProgress, Grid, Card, IconButton, CardMedia, CardContent, Pagination } from "@mui/material";
import SavedSearchIcon from '@mui/icons-material/SavedSearch';

export default function FavoritesPage() {
  const { favorites, loadFavorites, removeFavorite, page, totalPages, loading, isAuthenticated } = useFavorites();
  const navigate = useNavigate();

  useEffect(() => {
    loadFavorites(0);
  }, []);

  const handlePageChange = (_: any, value: number) => {
    loadFavorites(value - 1);
  };

  const openProperty = (id?: string) => {
    if (!id)
      return;
    navigate(`${AppRoute.Properties}/${id}`);
  };

  return (
    <Box sx={{ p: 3, minHeight: "100vh", display: "flex", flexDirection: "column", backgroundColor: 'white' }}>
      <Typography variant="h5" sx={{ mb: 2, fontWeight: 600, color: '#000000' }}>
        Favorite properties
      </Typography>

      {!isAuthenticated &&
        <Typography variant="h6" sx={{ mt: 8 }} >
          Create an account to see your saved properties!
        </Typography>
      }

      {isAuthenticated && totalPages == 0 &&
        <Box>
          <SavedSearchIcon sx={{ fontSize: 80, color: 'primary.main', mb: 4, mt: 8 }} />
          <Typography variant="h6" sx={{ mt: 8 }} >
            You currently have no saved property!
          </Typography>
        </Box>
      }

      {loading ? (
        <CircularProgress />
      ) : (
        <>
          <Grid container spacing={2}>
            {favorites.map((fav) => {
              const property = fav.property;
              return property && (
                <Grid size={{ xs: 12, md: 4 }} key={property.id}>
                  <Card
                    sx={{
                      borderRadius: 3,
                      cursor: "pointer",
                      position: "relative",
                      transition: "0.2s",
                      "&:hover": {
                        transform: "scale(1.02)",
                        boxShadow: 6,
                      },
                    }}
                    onClick={() => openProperty(property.id)}
                  >
                    <IconButton
                      onClick={(e) => {
                        e.stopPropagation();
                        removeFavorite(property.id || '');
                      }}
                      sx={{
                        position: "absolute",
                        top: 8,
                        right: 8,
                        background: "white",
                      }}
                    >
                      <DeleteIcon />
                    </IconButton>

                    <CardMedia
                      component="img"
                      height="200"
                      image={property.photoUrls?.[0] || "/"}
                    />

                    <CardContent>
                      <Typography variant="h6">
                        {property.title}
                      </Typography>

                      <Typography
                        variant="body2"
                        sx={{ color: "text.secondary" }}
                      >
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
              );
            })}
          </Grid>

          {isAuthenticated && totalPages > 0 &&
            <Box sx={{ mt: 8, display: "flex", justifyContent: "center" }}>
              <Pagination
                count={totalPages}
                page={page + 1}
                onChange={handlePageChange}
              />
            </Box>
          }
        </>
      )}
    </Box>
  );
}