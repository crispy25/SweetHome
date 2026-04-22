
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

export interface Property {
  id: string;
  title: string;
  description: string;
  address: string;
  rooms: number;
  bathrooms: number;
  usableArea: number;
  constructionYear: number;
  price: number;
  photoUrls: string[];
  propertyType: PropertyType;
  sellerId: number;
  createdAt: string;
  lastUpdated: string;
  
  comfort?: number;
  floor?: number;
  layout?: ApartmentLayout;
  
  floors?: number;
  landArea?: number;
  houseType?: HouseType;
}


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