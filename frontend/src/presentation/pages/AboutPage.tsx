import { useEffect, useState } from 'react';
import { useAuth } from '../../contexts/AuthContext';
import { FeedbackControllerApi, FeedbackOptionControllerApi, PageFeedbackResponseDto, FeedbackOptionResponseDto, FeedbackDto, CreateFeedbackRequest, Configuration, FeedbackDtoUserTypeEnum } from '../../infrastructure/apis/client';
import { Box, Typography, Stack, Paper, Rating, Button, TextField, Pagination, FormControl, Checkbox, FormLabel, ListItemText, MenuItem, Select, RadioGroup, FormControlLabel, Radio } from '@mui/material';

const authConfig = new Configuration({basePath: import.meta.env.VITE_APP_API_BASE_URL, accessToken: async () => {return localStorage.getItem('authToken') || '';}});
const feedbackApi = new FeedbackControllerApi(authConfig);
const optionApi = new FeedbackOptionControllerApi(authConfig);

export default function AboutPage() {
  const { token } = useAuth();

  const [pageData, setPageData] = useState<PageFeedbackResponseDto>();
  const [options, setOptions] = useState<FeedbackOptionResponseDto[]>([]);
  const [page, setPage] = useState(0);

  const [message, setMessage] = useState('');
  const [rating, setRating] = useState<number | null>(0);
  const [userType, setUserType] = useState<FeedbackDtoUserTypeEnum>(FeedbackDtoUserTypeEnum.Buyer);
  const [selectedOptions, setSelectedOptions] = useState<number[]>([]);

  const pageSize = 4;

  useEffect(() => {
    loadOptions();
  }, []);

  useEffect(() => {
    loadFeedbacks(page);
  }, [page]);

  const loadFeedbacks = async (pageNumber: number) => {
    try {
      const response = await feedbackApi.getAll1Raw({page: pageNumber, size: pageSize});
      const json = await response.raw.json() as PageFeedbackResponseDto;

      setPageData(json);
    } catch (err) {
      console.error(err);
    }
  };

  const loadOptions = async () => {
    try {
      const data = await optionApi.getAll2();
      setOptions(data || []);
    } catch (err) {
      console.error(err);
    }
  };

  const handleSubmit = async () => {
    if (!message)
      return;

    const dto: FeedbackDto = {
      message,
      rating: rating || undefined,
      userType,
      feedbackOptionIds: new Set(selectedOptions)
    };

    const feedback: CreateFeedbackRequest = { feedbackDto: dto };

    try {
      await feedbackApi.createFeedback(feedback);
      setMessage('');
      setRating(0);
      setSelectedOptions([]);
      loadFeedbacks(page);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <Box sx={{ height: '100vh', backgroundColor: '#ffffff' }}>
      <Box sx={{ py: 4, ml: 8, mr: 8}}>
        <Typography variant="h4" sx={{ fontWeight: 600, color: '#000000' }}> About Us </Typography>
        <Typography sx={{ mb: 4, color: 'black' }}>
          We are a modern property management platform focused on simplicity,
          transparency, and user experience. Our mission is to make property
          management easy and accessible for everyone.
        </Typography>

        <Box sx={{ display: "flex", justifyContent: "center", mt: 8 , mb: 8}}>
          <Box
            component="img"
            src="/AboutUs.webp"
            alt="Sweethome"
            sx={{ width: 700 }}
          />
        </Box>

        <Typography variant="h5" sx={{ mb: 4, color: 'black' }}>
          Customer reviews:
        </Typography>

        <Stack spacing={2} sx={{ mb: 2 }}>
          {pageData?.content?.map((f, i) => (
            <Paper key={i} sx={{ p: 3, backgroundColor: '#e7ebf0' }}>
              <Rating value={f.rating || 0} readOnly />
              <Typography>{f.message}</Typography>
            </Paper>
          ))}
        </Stack>

        <Stack spacing={2} sx={{ mb: 4, alignItems: "center" }}>
          <Pagination
            count={pageData?.totalPages || 1 + 1}
            page={(pageData?.number ?? 0) + 1}
            onChange={(_, value) => setPage(value - 1)}
            color="primary"
            variant="outlined"
            showFirstButton
            showLastButton
          />
        </Stack>

        {token && (
          <Paper sx={{ p: 3, backgroundColor: '#e7ebf0' }}>
            <Typography variant="h6" gutterBottom>
              <strong>Leave Feedback</strong>
            </Typography>

            <Rating
              value={rating}
              onChange={(_, val) => setRating(val)}
            />

          <Box>
            <FormControl sx={{ mt: 5 }}>
              <FormLabel>
                <Typography variant="h6">I used the website as a:</Typography>
              </FormLabel>
              <Box sx={{display:"flex", justifyContent: "center"}}>
              <RadioGroup
                value={userType}
                onChange={(e) => setUserType(e.target.value as 'BUYER' | 'SELLER')}
              >
                <FormControlLabel value={FeedbackDtoUserTypeEnum.Buyer} control={<Radio />} label="Buyer" />
                <FormControlLabel value={FeedbackDtoUserTypeEnum.Seller} control={<Radio />} label="Seller" />
              </RadioGroup>
              </Box>
            </FormControl>
          </Box>
            <TextField
              fullWidth
              multiline
              rows={3}
              label="Message"
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              sx={{ mt: 2 }}
            />

            <Typography sx={{ mt: 2, mb: 1 }}>
              Select options:
            </Typography>

            <FormControl fullWidth sx={{ mt: 2 }}>
              <FormLabel>Options</FormLabel>

              <Select
                multiple
                value={selectedOptions}
                onChange={(e) => setSelectedOptions(e.target.value as number[])}
                renderValue={(selected) =>
                  options
                    .filter(o => selected.includes(o.id!))
                    .map(o => o.name)
                    .join(', ')
                }
              >
                {options.map((opt) => (
                  <MenuItem key={opt.id} value={opt.id!}>
                    <Checkbox checked={selectedOptions.includes(opt.id!)} />
                    <ListItemText primary={opt.name} />
                  </MenuItem>
                ))}
              </Select>
            </FormControl>

            <Button variant="contained" sx={{ mt: 2 }} onClick={handleSubmit}> Submit </Button>
          </Paper>
        )}
      </Box>
    </Box>

  );
}
