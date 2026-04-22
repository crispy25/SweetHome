import React, { createContext, useContext, useEffect, useState } from "react";
import { Configuration, FavoriteControllerApi, FavoriteResponseDto, PageFavoriteResponseDto } from "../infrastructure/apis/client";
import { useAuth } from "./AuthContext";
import { toast } from "react-toastify";


interface FavoritesContextType {
  favorites: FavoriteResponseDto[];
  page: number;
  totalPages: number;
  loading: boolean;
  loadFavorites: (page?: number) => Promise<void>;
  removeFavorite: (propertyId: string) => Promise<void>;
  isFavorite: (id: string) => Promise<boolean>;
  isAuthenticated: boolean;
}

const authConfig = new Configuration({basePath: import.meta.env.VITE_APP_API_BASE_URL, accessToken: async () => {return localStorage.getItem('authToken') || '';},});
const favoriteApi = new FavoriteControllerApi(authConfig);

const FavoritesContext = createContext<FavoritesContextType | undefined>(undefined);

export function FavoritesProvider({ children }: { children: React.ReactNode }) {
  const { isLoading, isAuthenticated } = useAuth();

  const [favorites, setFavorites] = useState<FavoriteResponseDto[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (isLoading || !isAuthenticated)
      return;

    loadFavorites(0);
  }, [isLoading, isAuthenticated]);

  const loadFavorites = async (pageNumber: number = 0) => {
    if (!isAuthenticated)
      return;

    setLoading(true);

    try {
      const response = await favoriteApi.getAll3Raw({page: pageNumber});
      const json = (await response.raw.json()) as PageFavoriteResponseDto;

      setFavorites(json.content ?? []);
      setPage(json.number ?? 0);
      setTotalPages(json.totalPages ?? 0);
    } catch (err) {
      console.error("Failed to fetch favorites:", err);
      setFavorites([]);
    } finally {
      setLoading(false);
    }
  };

  const isFavorite = async (id: string) => {
    if (!isAuthenticated || !id)
      return false;

    try {
      return await favoriteApi.isFavorite({propertyId: id});
    } catch (err) {
      console.error("Failed to check if property is in favorites:", err);
      return false;
    }
  };

  const removeFavorite = async (propertyId: string) => {
    if (!isAuthenticated || !favoriteApi)
      return;

    try {
      await favoriteApi.removeFavorite({ propertyId });

      setFavorites((prev) => prev.filter((f) => f.property?.id !== propertyId));
      toast.success('Remove property from favorites!')
    } catch (err) {
      console.error("Failed to remove favorite:", err);
    }
  };

  return (
    <FavoritesContext.Provider
      value={{
        favorites,
        page,
        totalPages,
        loading,
        loadFavorites,
        removeFavorite,
        isFavorite,
        isAuthenticated
      }}
    >
      {children}
    </FavoritesContext.Provider>
  );
}

export function useFavorites() {
  const ctx = useContext(FavoritesContext);
  if (!ctx)
    throw new Error("useFavorites must be used within provider");
  return ctx;
}