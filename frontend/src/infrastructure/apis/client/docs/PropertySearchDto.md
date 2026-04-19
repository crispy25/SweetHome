
# PropertySearchDto


## Properties

Name | Type
------------ | -------------
`minRooms` | number
`maxRooms` | number
`minBathrooms` | number
`maxBathrooms` | number
`minPrice` | number
`maxPrice` | number
`minUsableArea` | number
`maxUsableArea` | number
`minConstructionYear` | number
`maxConstructionYear` | number
`propertyType` | string
`createdAfter` | Date
`createdBefore` | Date
`minFloor` | number
`maxFloor` | number
`comfort` | number
`layout` | string
`minFloors` | number
`maxFloors` | number
`minLandArea` | number
`maxLandArea` | number
`houseType` | string

## Example

```typescript
import type { PropertySearchDto } from ''

// TODO: Update the object below with actual values
const example = {
  "minRooms": 2,
  "maxRooms": 5,
  "minBathrooms": 1,
  "maxBathrooms": 3,
  "minPrice": 1000.0,
  "maxPrice": 100000.0,
  "minUsableArea": 50.0,
  "maxUsableArea": 100.0,
  "minConstructionYear": 1990,
  "maxConstructionYear": 2010,
  "propertyType": null,
  "createdAfter": 1970-01-01T00:00Z,
  "createdBefore": null,
  "minFloor": 1,
  "maxFloor": 10,
  "comfort": 1,
  "layout": DETACHED,
  "minFloors": 1,
  "maxFloors": 3,
  "minLandArea": 50.0,
  "maxLandArea": 100.0,
  "houseType": DETACHED,
} satisfies PropertySearchDto

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PropertySearchDto
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


