import React, { createContext, useContext, useState, useEffect } from 'react';
import { type UserDto as User, type LoginDto as LoginCredentials, type RegisterDto as RegisterData, Configuration } from '../infrastructure/apis/client';
import { AuthControllerApi } from '../infrastructure/apis/client';

const authConfig = new Configuration({basePath: import.meta.env.VITE_APP_API_BASE_URL, accessToken: async () => {return localStorage.getItem('authToken') || '';},});
const authApi = new AuthControllerApi(authConfig);


export enum UserRole {
  USER = 'USER',
  ADMIN = 'ADMIN'
}

interface AuthContextType {
  user: User | null;
  token: string | null;
  login: (credentials: LoginCredentials) => Promise<boolean>;
  register: (data: RegisterData) => Promise<boolean>;
  logout: () => void;
  isAuthenticated: boolean;
  isLoading: boolean;
}

const AuthContext = createContext<AuthContextType | null>(null);

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const [token, setToken] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const init = async () => {
      try {
        const storedToken = localStorage.getItem('authToken');
        const storedUser = localStorage.getItem('currentUser');

        if (!storedToken || !storedUser) {
          setIsLoading(false);
          return;
        }

        setToken(storedToken);

        await authApi.validateToken();

        setUser(JSON.parse(storedUser));
      } catch (err) {
        console.error('Token invalid:', err);
        logout();
      } finally {
        setIsLoading(false);
      }
    };

    init();
  }, []);

  const login = async (credentials: LoginCredentials): Promise<boolean> => {
    try {
      const result: any = await authApi.login({loginDto: credentials});

      const loggedInUser: User = {
        id: result.user.id,
        email: result.user.email,
        username: result.user.username,
        phoneNumber: result.user.phone,
        roles: result.user.role ?? [UserRole.USER],
      };

      setUser(loggedInUser);
      setToken(result.token);
      localStorage.setItem('authToken', result.token);
      localStorage.setItem('currentUser', JSON.stringify(loggedInUser));

      return true;
    } catch (error) {
      console.error('Login error:', error);
      return false;
    }
  };

  const register = async (data: RegisterData): Promise<boolean> => {
    try {
      await authApi.register({registerDto: data});
      return true;
    } catch (error) {
      console.error('Registration error:', error);
      return false;
    }
  };

  const logout = () => {
    setUser(null);
    setToken(null);
    localStorage.removeItem('currentUser');
    localStorage.removeItem('authToken');
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        token,
        login,
        register,
        logout,
        isAuthenticated: !!user,
        isLoading
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth(): AuthContextType {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return context;
}
