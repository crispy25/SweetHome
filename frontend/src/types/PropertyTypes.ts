
export enum PropertyType {
  HOUSE = "HOUSE",
  APARTMENT = "APARTMENT",
}

export enum ApartmentLayout {
  DETACHED = 'DETACHED',
  SEMI_DETACHED = 'SEMI_DETACHED',
  NON_DETACHED = 'NON_DETACHED',
  CIRCULAR = 'CIRCULAR'
}

export enum HouseType {
  DETACHED = 'DETACHED',
  DUPLEX = 'DUPLEX',
  TERRACED = 'TERRACED',
  TRIPLEX = 'TRIPLEX',
  OTHER = 'OTHER'
}

export interface BaseProperty {
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
}

export interface ApartmentProperty extends BaseProperty {
  propertyType: PropertyType.APARTMENT;
  comfort?: number;
  floor?: number;
  layout?: string;
}

export interface HouseProperty extends BaseProperty {
  propertyType: PropertyType.HOUSE;
  floors?: number;
  landArea?: number;
  houseType?: string;
}

export type Property = ApartmentProperty | HouseProperty;


export interface Favorite {
  property: Property;
  createdAt: string;
}

export const getApartmentLayoutLabel = (layout: ApartmentLayout): string => {
  const labels: Record<ApartmentLayout, string> = {
    [ApartmentLayout.DETACHED]: 'Detached',
    [ApartmentLayout.SEMI_DETACHED]: 'Semi-Detached',
    [ApartmentLayout.NON_DETACHED]: 'Non-Detached',
    [ApartmentLayout.CIRCULAR]: 'Circular'
  };
  return labels[layout];
};

export const getHouseTypeLabel = (houseType: HouseType): string => {
  const labels: Record<HouseType, string> = {
    [HouseType.DETACHED]: 'Detached',
    [HouseType.DUPLEX]: 'Duplex',
    [HouseType.TRIPLEX]: 'Triplex',
    [HouseType.TERRACED]: 'Terraced',
    [HouseType.OTHER]: 'Other',
  };
  return labels[houseType];
};