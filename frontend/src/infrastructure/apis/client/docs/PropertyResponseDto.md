
# PropertyResponseDto


## Properties

Name | Type
------------ | -------------
`id` | string
`title` | string
`description` | string
`address` | string
`rooms` | number
`bathrooms` | number
`usableArea` | number
`constructionYear` | number
`price` | number
`photoUrls` | Array&lt;string&gt;
`sellerId` | string
`propertyType` | string
`createdAt` | Date
`lastUpdated` | Date

## Example

```typescript
import type { PropertyResponseDto } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "title": null,
  "description": null,
  "address": null,
  "rooms": null,
  "bathrooms": null,
  "usableArea": null,
  "constructionYear": null,
  "price": null,
  "photoUrls": null,
  "sellerId": null,
  "propertyType": null,
  "createdAt": null,
  "lastUpdated": null,
} satisfies PropertyResponseDto

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PropertyResponseDto
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


