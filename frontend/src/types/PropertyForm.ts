import { PropertyType } from "./PropertyType";

export type PropertyForm = {
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