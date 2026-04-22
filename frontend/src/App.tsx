import { Route, Routes } from "react-router-dom";
import { AppRoute } from "./routes";
import { AuthProvider } from "./contexts/AuthContext";
import { FavoritesProvider } from "./contexts/FavoritesContext";
import { ToastContainer } from "react-toastify";
import { HomePage } from "./presentation/pages/HomePage";
import LoginPage from "./presentation/pages/LoginPage";
import RegisterPage from "./presentation/pages/RegisterPage";
import AboutPage from "./presentation/pages/AboutPage";
import NavBar from "./presentation/components/AppBar";
import PropertiesPage from "./presentation/pages/PropertiesPage";
import FavoritesPage from "./presentation/pages/FavoritesPage";
import { PropertyDetailPage } from "./presentation/pages/PropertyDetailPage";

export function App() {
  return <AuthProvider>
          <FavoritesProvider>
            <NavBar />
            <ToastContainer />
              <Routes>
                <Route path={AppRoute.Home} element={<HomePage />} />
                <Route path={AppRoute.Login} element={<LoginPage />} />
                <Route path={AppRoute.Register} element={<RegisterPage />} />
                <Route path={AppRoute.Properties} element={<PropertiesPage />} />
                <Route path="/properties/:id" element={<PropertyDetailPage />} />
                <Route path={AppRoute.About} element={<AboutPage />} />
                <Route path={AppRoute.Favorites} element={<FavoritesPage />} />
              </Routes>
          </FavoritesProvider>
        </AuthProvider>
}