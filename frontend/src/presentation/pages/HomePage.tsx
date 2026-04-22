import { Link as RouterLink } from 'react-router-dom'
import { Box, Container, Typography, Button, Grid, Card, CardContent, Stack } from '@mui/material'
import { AppRoute } from '../../routes'


import SearchIcon from '@mui/icons-material/Search'
import HomeIcon from '@mui/icons-material/Home'
import ShieldIcon from '@mui/icons-material/Shield'
import ApartmentIcon from '@mui/icons-material/Apartment'

export function HomePage() {
  return (
    <Box sx={{ width: '100%', overflowX: 'hidden', backgroundColor: 'white' }}>
      <Box
        sx={{
          py: 12,
          textAlign: 'center',
          background: 'linear-gradient(to bottom, #7e92e6, #ffffff)',
        }}
      >
        <Container maxWidth={false} disableGutters>

          <ApartmentIcon sx={{ fontSize: 80, color: 'primary.main', mb: 4 }} />

          <Typography variant="h3" sx={{ color: "black" }} gutterBottom>
            Find Your Dream Home
          </Typography>

          <Typography variant="h6" color="primary" sx={{ mb: 4 }}>
            Your trusted platform for real estate properties.
            Discover apartments and houses tailored for you.
          </Typography>

          <Stack
            direction="row"
            spacing={2}
            sx={{
              justifyContent: "center",
              alignItems: "center",
            }}>
            <Button
              variant="contained"
              size="large"
              startIcon={<SearchIcon />}
              component={RouterLink}
              to={AppRoute.Properties}
            >
              Search Properties
            </Button>

            <Button
              variant="outlined"
              size="large"
              component={RouterLink}
              to={AppRoute.Register}
            >
              Sign Up Free
            </Button>
          </Stack>

        </Container>
      </Box>

      <Container sx={{ py: 10 }}>
        <Typography variant="h4" align="center" gutterBottom>
          Why SweetHome?
        </Typography>

        <Grid container spacing={4} sx={{ mt: 2 }}>

          <Grid size={{ xs: 12, md: 4 }}>
            <Card>
              <CardContent sx={{ textAlign: 'center' }}>
                <SearchIcon color="primary" sx={{ fontSize: 40, mb: 1 }} />
                <Typography variant="h6">Advanced Search</Typography>
                <Typography color="text.secondary">
                  Filter by price and features to find your ideal property.
                </Typography>
              </CardContent>
            </Card>
          </Grid>

          <Grid size={{ xs: 12, md: 4 }}>
            <Card>
              <CardContent sx={{ textAlign: 'center' }}>
                <HomeIcon color="primary" sx={{ fontSize: 40, mb: 1 }} />
                <Typography variant="h6">Property Variety</Typography>
                <Typography color="text.secondary">
                  Apartments and houses for all budgets and preferences.
                </Typography>
              </CardContent>
            </Card>
          </Grid>

          <Grid size={{ xs: 12, md: 4 }}>
            <Card>
              <CardContent sx={{ textAlign: 'center' }}>
                <ShieldIcon color="primary" sx={{ fontSize: 40, mb: 1 }} />
                <Typography variant="h6">Verified Listings</Typography>
                <Typography color="text.secondary">
                  All listings are checked for safety and authenticity.
                </Typography>
              </CardContent>
            </Card>
          </Grid>

        </Grid>
      </Container>

      <Box sx={{ py: 8, bgcolor: 'primary.main', color: 'white', boxShadow: 10 }}>
        <Container>
          <Grid container spacing={4}>

            <Grid size={{ xs: 12, md: 4 }}>
              <Typography variant="h4">1000+</Typography>
              <Typography>Active Properties</Typography>
            </Grid>

            <Grid size={{ xs: 12, md: 4 }}>
              <Typography variant="h4">500+</Typography>
              <Typography>Satisfied Users</Typography>
            </Grid>

            <Grid size={{ xs: 12, md: 4 }}>
              <Typography variant="h4">50+</Typography>
              <Typography>Cities Covered</Typography>
            </Grid>

          </Grid>
        </Container>
      </Box>

      <Container sx={{ py: 10, textAlign: 'center' }}>
        <Typography variant="h4" gutterBottom>
          Start Searching Today!
        </Typography>

        <Typography color="text.secondary" sx={{ mb: 3 }}>
          Create a free account and explore thousands of listings.
        </Typography>

        <Button
          variant="contained"
          size="large"
          component={RouterLink}
          to={AppRoute.Register}
        >
          Create Free Account
        </Button>
      </Container>

    </Box>
  )
}