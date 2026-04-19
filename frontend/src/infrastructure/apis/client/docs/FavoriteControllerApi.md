# FavoriteControllerApi

All URIs are relative to *http://localhost:8090*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addFavorite**](FavoriteControllerApi.md#addfavorite) | **POST** /api/v1/favorites/{propertyId} |  |
| [**getAll3**](FavoriteControllerApi.md#getall3) | **GET** /api/v1/favorites |  |
| [**removeFavorite**](FavoriteControllerApi.md#removefavorite) | **DELETE** /api/v1/favorites/{propertyId} |  |



## addFavorite

> FavoriteResponseDto addFavorite(propertyId)



### Example

```ts
import {
  Configuration,
  FavoriteControllerApi,
} from '';
import type { AddFavoriteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new FavoriteControllerApi(config);

  const body = {
    // string
    propertyId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies AddFavoriteRequest;

  try {
    const data = await api.addFavorite(body);
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
| **propertyId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**FavoriteResponseDto**](FavoriteResponseDto.md)

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


## getAll3

> PageFavoriteResponseDto getAll3(page, size)



### Example

```ts
import {
  Configuration,
  FavoriteControllerApi,
} from '';
import type { GetAll3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new FavoriteControllerApi(config);

  const body = {
    // number (optional)
    page: 56,
    // number (optional)
    size: 56,
  } satisfies GetAll3Request;

  try {
    const data = await api.getAll3(body);
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
| **size** | `number` |  | [Optional] [Defaults to `10`] |

### Return type

[**PageFavoriteResponseDto**](PageFavoriteResponseDto.md)

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


## removeFavorite

> removeFavorite(propertyId)



### Example

```ts
import {
  Configuration,
  FavoriteControllerApi,
} from '';
import type { RemoveFavoriteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new FavoriteControllerApi(config);

  const body = {
    // string
    propertyId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies RemoveFavoriteRequest;

  try {
    const data = await api.removeFavorite(body);
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
| **propertyId** | `string` |  | [Defaults to `undefined`] |

### Return type

`void` (Empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

