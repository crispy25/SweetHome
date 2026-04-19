# FeedbackControllerApi

All URIs are relative to *http://localhost:8090*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createFeedback**](FeedbackControllerApi.md#createfeedback) | **POST** /api/v1/feedback |  |
| [**getAll1**](FeedbackControllerApi.md#getall1) | **GET** /api/v1/feedback |  |



## createFeedback

> FeedbackResponseDto createFeedback(feedbackDto)



### Example

```ts
import {
  Configuration,
  FeedbackControllerApi,
} from '';
import type { CreateFeedbackRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new FeedbackControllerApi(config);

  const body = {
    // FeedbackDto
    feedbackDto: ...,
  } satisfies CreateFeedbackRequest;

  try {
    const data = await api.createFeedback(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **feedbackDto** | [FeedbackDto](FeedbackDto.md) |  | |

### Return type

[**FeedbackResponseDto**](FeedbackResponseDto.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getAll1

> PageFeedbackResponseDto getAll1(page, size, sortBy, order)



### Example

```ts
import {
  Configuration,
  FeedbackControllerApi,
} from '';
import type { GetAll1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new FeedbackControllerApi(config);

  const body = {
    // number (optional)
    page: 56,
    // number (optional)
    size: 56,
    // 'CREATED_AT' | 'RATING' (optional)
    sortBy: sortBy_example,
    // 'ASC' | 'DESC' (optional)
    order: order_example,
  } satisfies GetAll1Request;

  try {
    const data = await api.getAll1(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **page** | `number` |  | [Optional] [Defaults to `0`] |
| **size** | `number` |  | [Optional] [Defaults to `5`] |
| **sortBy** | `CREATED_AT`, `RATING` |  | [Optional] [Defaults to `&#39;CREATED_AT&#39;`] [Enum: CREATED_AT, RATING] |
| **order** | `ASC`, `DESC` |  | [Optional] [Defaults to `&#39;DESC&#39;`] [Enum: ASC, DESC] |

### Return type

[**PageFeedbackResponseDto**](PageFeedbackResponseDto.md)

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

