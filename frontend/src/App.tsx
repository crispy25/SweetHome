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
import PropertyEditPage from "./presentation/pages/PropertyEditPage";

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
                <Route path={`${AppRoute.Properties}/:id`} element={<PropertyDetailPage />} />
                <Route path={`${AppRoute.Properties}/edit/:id`} element={<PropertyEditPage />} /> 
                <Route path={`${AppRoute.Properties}/create`} element={<PropertyEditPage />} />
                <Route path={AppRoute.Favorites} element={<FavoritesPage />} />
                <Route path={AppRoute.About} element={<AboutPage />} />
              </Routes>
          </FavoritesProvider>
        </AuthProvider>
}