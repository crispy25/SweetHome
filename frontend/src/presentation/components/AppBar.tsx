import * as React from 'react';
import { useNavigate } from 'react-router-dom';

import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import HomeIcon from '@mui/icons-material/Home';
import { useAuth } from "../../contexts/AuthContext";
import { AppRoute } from "../../routes";

const pages = [
    { label: 'Properties', path: AppRoute.Properties },
    { label: 'Favorites', path: AppRoute.Favorites },
    { label: 'About', path: AppRoute.About }
];

const settings = ['My properties', 'Logout'];

function NavBar() {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const [, setAnchorElNav] = React.useState<null | HTMLElement>(null);
  const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(null);
    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => setAnchorElNav(null);
  const handleCloseUserMenu = () => setAnchorElUser(null);

  const handleNavigate = (path: string) => {
    navigate(path);
    handleCloseNavMenu();
  };

  const handleSettingClick = (setting: string) => {
    handleCloseUserMenu();

    if (setting === 'Logout') {
      logout();
      navigate(AppRoute.Home);
    }
  };

  return (
      <AppBar position="static">
        <Toolbar sx={{ px: 4 }} disableGutters>
          <HomeIcon
            sx={{ display: { xs: 'none', md: 'flex' }, mr: 1, cursor: 'pointer' }}
            onClick={() => navigate(AppRoute.Home)}
          />

          <Typography
            variant="h6"
            noWrap
            sx={{
              mr: 7,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'Helvetica',
              fontWeight: 700,
              color: 'inherit',
              cursor: 'pointer'
            }}
            onClick={() => navigate(AppRoute.Home)}
          >
            SweetHome
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
            {pages.map((page) => (
              <Button
                key={page.label}
                onClick={() => handleNavigate(page.path)}
                sx={{ my: 2, mx: 4, color: 'white' }}
              >
                {page.label}
              </Button>
            ))}
          </Box>

          <Box sx={{ flexGrow: 0 }}>
            {!user ? (<Button color="inherit" onClick={() => navigate(AppRoute.Login)}>Login</Button>) : (
              <>
                <Tooltip title="Open settings">
                  <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                    <Avatar alt={user.username || 'Me'} />
                  </IconButton>
                </Tooltip>

                <Menu anchorEl={anchorElUser} open={Boolean(anchorElUser)} onClose={handleCloseUserMenu}>
                  {settings.map((setting) => (
                    <MenuItem key={setting} onClick={() => handleSettingClick(setting)}>
                      <Typography>{setting}</Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </>
            )}
          </Box>
        </Toolbar>
      </AppBar>
  );
}

export default NavBar;