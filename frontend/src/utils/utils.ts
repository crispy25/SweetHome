import { PropertyForm } from "../types/PropertyForm";
import { PropertyType } from "../types/PropertyType";


const BASE_API_URL = import.meta.env.VITE_APP_API_BASE_URL + "/api/v1";

export function mapToPropertyForm(dto: any): PropertyForm {
  if (dto.propertyType === PropertyType.APARTMENT)
    return { ...dto, propertyType: PropertyType.APARTMENT };

  if (dto.propertyType === PropertyType.HOUSE)
    return { ...dto, propertyType: PropertyType.HOUSE };

  throw new Error('Invalid property type');
}

export async function getPropertyForm(id: string): Promise<PropertyForm> {
  console.log(0);
  const response = await fetch(`${BASE_API_URL}/properties/${id}`);
  const data = await response.json();
  console.log(data);
  console.log(1);
  const x = mapToPropertyForm(data);
  console.log(x);
  console.log(2);
  return x;
}