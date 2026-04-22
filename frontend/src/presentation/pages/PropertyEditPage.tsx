import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';

import ImageUploader from '../components/ImageUploader';
import { AppRoute } from '../../routes';
import { PropertyForm } from '../../types/PropertyForm';
import { PropertyType } from '../../types/PropertyType';
import { TextField, Box, Card, CardContent, Typography, Grid, MenuItem, Divider, Button, Stack } from '@mui/material';
import { Configuration, ApartmentControllerApi, HouseControllerApi } from '../../infrastructure/apis/client';
import { getPropertyForm } from '../../utils/utils';

const authConfig = new Configuration({basePath: import.meta.env.VITE_APP_API_BASE_URL, accessToken: async () => localStorage.getItem('authToken') || ''});
const apartmentApi = new ApartmentControllerApi(authConfig);
const houseApi = new HouseControllerApi(authConfig);

export default function PropertyFormPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const isEdit = Boolean(id);

  const [form, setForm] = useState<PropertyForm>({
    title: '',
    description: '',
    address: '',
    rooms: undefined,
    bathrooms: undefined,
    usableArea: undefined,
    constructionYear: new Date().getFullYear(),
    price: undefined,
    photoUrls: [],
    propertyType: PropertyType.APARTMENT,
  });

  const update = (key: any , value: any) => {setForm((prev) => ({ ...prev, [key]: value }));};

  useEffect(() => {
    if (!id)
      return;

    try {
      getPropertyForm(id).then((data) => setForm(data));
    } catch (err) {
      console.error(err);
      toast.error('Failed to load property');
    }
  }, [id]);

  const renderField = (key: keyof PropertyForm, label: string, type: string = 'text') => (
    <TextField
      fullWidth
      label={label}
      type={type}
      value={form[key] ?? ''}
      onChange={(e) => update(key, type === 'number' ? Number(e.target.value) : e.target.value)}
    />
  );

  const handleSubmit = async () => {
    try {
      const isApartment = form.propertyType === PropertyType.APARTMENT;

      const baseDto = {
        title: form.title,
        description: form.description,
        address: form.address,
        rooms: form.rooms,
        bathrooms: form.bathrooms,
        usableArea: form.usableArea,
        constructionYear: form.constructionYear,
        price: form.price,
        photoUrls: form.photoUrls,
      };

      const fullDto = isApartment ? {...baseDto, comfort: Number(form.comfort), floor: Number(form.floor), layout: form.layout} :
                                {...baseDto, floors: Number(form.floors), landArea: Number(form.landArea), houseType: form.houseType};
      const dto = Object.fromEntries(Object.entries(fullDto).filter(([, v]) => v != null && v != undefined && !Number.isNaN(v)));

      if (isApartment)
        isEdit ? await apartmentApi.patch1({ id: id, apartmentPatchDto: dto } as any) : await apartmentApi.create1({ apartmentDto: dto } as any);
      else
        isEdit ? await houseApi.patch({ id: id, housePatchDto: dto } as any) : await houseApi.create({ houseDto: dto } as any);

      toast.success(isEdit ? 'Updated' : 'Created');
      navigate(AppRoute.Properties);
    } catch (e) {
      console.error(e);
      toast.error('Failed to save');
    }
  };

  return (
    <Box sx={{ p: 4, bgcolor: '#f5f5f5', minHeight: '100vh' }}>
      <Card sx={{ maxWidth: 900, mx: 'auto', borderRadius: 3 }}>
        <CardContent sx={{ p: 4 }}>
          <Typography variant="h4">
            {isEdit ? 'Edit Property' : 'Create Property'}
          </Typography>

          <Grid container spacing={2}>
            <Grid size={{ xs: 12 }}>
              {!isEdit &&
                <TextField
                  select
                  fullWidth
                  label="Property Type"
                  value={form.propertyType}
                  onChange={(e) => update('propertyType', e.target.value as PropertyType)}
                >
                  <MenuItem value={PropertyType.APARTMENT}>
                    Apartment
                  </MenuItem>
                  <MenuItem value={PropertyType.HOUSE}>
                    House
                  </MenuItem>
                </TextField>
              }
            </Grid>
            <Grid size={{ sm: 6 }}>{renderField('title', 'Title')}</Grid>
            <Grid size={{ sm: 6 }}>{renderField('description', 'Description')}</Grid>
            <Grid size={{ sm: 6 }}>{renderField('address', 'Address')}</Grid>
            <Grid size={{ sm: 4 }}>{renderField('rooms', 'Rooms', 'number')}</Grid>
            <Grid size={{ sm: 4 }}> {renderField('bathrooms', 'Bathrooms', 'number')}</Grid>
            <Grid size={{ sm: 4 }}> {renderField('usableArea', 'Area (m²)', 'number')} </Grid>
            <Grid size={{ sm: 6 }}> {renderField('price', 'Price', 'number')}</Grid>
            <Grid size={{ sm: 6 }}>{renderField('constructionYear', 'Construction Year', 'number')}</Grid>
            <Grid size={{ xs: 12 }}>
              <Divider sx={{ my: 2 }} />
              <Typography>Images</Typography>
              <ImageUploader onChange={(urls) => update('photoUrls', urls)}/>
            </Grid>
              
            {form.propertyType === PropertyType.APARTMENT && (
              <>
                <Grid size={{ xs: 12, sm: 4 }}>{renderField('comfort', 'Comfort', 'number')}</Grid>
                <Grid size={{ xs: 12, sm: 4 }}>{renderField('floor', 'Floor', 'number')}</Grid>
                <Grid size={{ xs: 12, sm: 4 }}>{renderField('layout', 'Layout')}</Grid>
              </>
            )}

            {form.propertyType === PropertyType.HOUSE && (
              <>
                <Grid size={{ xs: 12, sm: 4 }}>{renderField('floors', 'Floors', 'number')}</Grid>
                <Grid size={{ xs: 12, sm: 4 }}>{renderField('landArea', 'Land Area', 'number')}</Grid>
                <Grid size={{ xs: 12, sm: 4 }}>{renderField('houseType', 'House Type')}</Grid>
              </>
            )}

            <Grid size={{ xs: 12 }}>
              <Stack direction="row" sx={{justifyContent: "flex-end"}}>
                <Button variant="contained" size="large" onClick={handleSubmit}>{isEdit ? 'Update' : 'Create'}</Button>
              </Stack>
            </Grid>
          </Grid>
        </CardContent>
      </Card>
    </Box>
  );
}