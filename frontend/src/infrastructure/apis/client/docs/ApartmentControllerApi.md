# ApartmentControllerApi

All URIs are relative to *http://localhost:8090*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create1**](ApartmentControllerApi.md#create1) | **POST** /api/v1/apartments |  |
| [**patch1**](ApartmentControllerApi.md#patch1) | **PATCH** /api/v1/apartments/{id} |  |
| [**update1**](ApartmentControllerApi.md#update1) | **PUT** /api/v1/apartments/{id} |  |



## create1

> PropertyResponseDto create1(apartmentDto)



### Example

```ts
import {
  Configuration,
  ApartmentControllerApi,
} from '';
import type { Create1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ApartmentControllerApi(config);

  const body = {
    // ApartmentDto
    apartmentDto: ...,
  } satisfies Create1Request;

  try {
    const data = await api.create1(body);
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
| **apartmentDto** | [ApartmentDto](ApartmentDto.md) |  | |

### Return type

[**PropertyResponseDto**](PropertyResponseDto.md)

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


## patch1

> PropertyResponseDto patch1(id, apartmentPatchDto)



### Example

```ts
import {
  Configuration,
  ApartmentControllerApi,
} from '';
import type { Patch1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ApartmentControllerApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // ApartmentPatchDto
    apartmentPatchDto: ...,
  } satisfies Patch1Request;

  try {
    const data = await api.patch1(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **apartmentPatchDto** | [ApartmentPatchDto](ApartmentPatchDto.md) |  | |

### Return type

[**PropertyResponseDto**](PropertyResponseDto.md)

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


## update1

> PropertyResponseDto update1(id, apartmentDto)



### Example

```ts
import {
  Configuration,
  ApartmentControllerApi,
} from '';
import type { Update1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ApartmentControllerApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // ApartmentDto
    apartmentDto: ...,
  } satisfies Update1Request;

  try {
    const data = await api.update1(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **apartmentDto** | [ApartmentDto](ApartmentDto.md) |  | |

### Return type

[**PropertyResponseDto**](PropertyResponseDto.md)

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

