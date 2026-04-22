import { SyntheticEvent, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { Container, Box, Paper, Typography, Alert, TextField, Button, CircularProgress } from '@mui/material';
import { useAuth } from '../../contexts/AuthContext';
import { AppRoute } from '../../routes';

export default function Login() {
  const navigate = useNavigate();
  const { login } = useAuth();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: SyntheticEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError('');

    if (!email || !password) {
      setError('Please fill in all fields');
      return;
    }

    setLoading(true);

    try {
      const ok = await login({ email, password });

      if (ok)
        navigate(AppRoute.Properties);
      else
        setError('Invalid email or password');
    } catch {
      setError('Login failed. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth="sm">
      <Box
        sx={{
          display: 'flex',
          minHeight: '90vh',
          justifyContent: 'center',
          alignItems: 'center',
        }}
      >
        <Paper elevation={3} sx={{ p: 4, width: '100%' }}>
          <Typography  variant="h5" align="center" gutterBottom>
            <strong>Login</strong>
          </Typography>

          <Typography color='info' sx={{ textAlign: 'center', mb: 2 }}>
            Enter your credentials
          </Typography>

          {error && (
            <Alert severity="error" sx={{ mb: 2 }}>
              {error}
            </Alert>
          )}

          <Box component="form" onSubmit={handleSubmit}>
            <TextField
              fullWidth
              label="Email"
              type="email"
              margin="normal"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />

            <TextField
              fullWidth
              label="Password"
              type="password"
              margin="normal"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />

            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 2 }}
              disabled={loading}
            >
              {loading ? <CircularProgress size={24} /> : 'Login'}
            </Button>

            <Typography variant="body2" align="center" sx={{ mt: 2 }}>
              Don't have an account?{' '}
              <Link to={AppRoute.Register}>Sign up</Link>
            </Typography>
          </Box>
        </Paper>
      </Box>
    </Container>
  );
}