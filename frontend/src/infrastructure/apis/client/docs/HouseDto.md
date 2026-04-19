
# HouseDto


## Properties

Name | Type
------------ | -------------
`title` | string
`description` | string
`address` | string
`rooms` | number
`bathrooms` | number
`usableArea` | number
`constructionYear` | number
`price` | number
`photoUrls` | Array&lt;string&gt;
`floors` | number
`landArea` | number
`houseType` | string

## Example

```typescript
import type { HouseDto } from ''

// TODO: Update the object below with actual values
const example = {
  "title": Property for sale!,
  "description": Spacious garden, balcony and parking,
  "address": 123 Main Street, Springfield,
  "rooms": 4,
  "bathrooms": 2,
  "usableArea": 100.0,
  "constructionYear": null,
  "price": 100000.0,
  "photoUrls": ["GardenPhoto.png","RoomPhoto.png"],
  "floors": 2,
  "landArea": 500.0,
  "houseType": DETACHED,
} satisfies HouseDto

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as HouseDto
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


