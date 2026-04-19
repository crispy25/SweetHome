# FeedbackOptionControllerApi

All URIs are relative to *http://localhost:8090*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAll2**](FeedbackOptionControllerApi.md#getall2) | **GET** /api/v1/feedback-options |  |



## getAll2

> Array&lt;FeedbackOptionResponseDto&gt; getAll2()



### Example

```ts
import {
  Configuration,
  FeedbackOptionControllerApi,
} from '';
import type { GetAll2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new FeedbackOptionControllerApi(config);

  try {
    const data = await api.getAll2();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**Array&lt;FeedbackOptionResponseDto&gt;**](FeedbackOptionResponseDto.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

