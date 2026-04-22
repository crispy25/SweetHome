
# PageFavoriteResponseDto


## Properties

Name | Type
------------ | -------------
`totalPages` | number
`totalElements` | number
`size` | number
`content` | [Array&lt;FavoriteResponseDto&gt;](FavoriteResponseDto.md)
`number` | number
`numberOfElements` | number
`sort` | [Array&lt;SortObject&gt;](SortObject.md)
`pageable` | [PageableObject](PageableObject.md)
`first` | boolean
`last` | boolean
`empty` | boolean

## Example

```typescript
import type { PageFavoriteResponseDto } from ''

// TODO: Update the object below with actual values
const example = {
  "totalPages": null,
  "totalElements": null,
  "size": null,
  "content": null,
  "number": null,
  "numberOfElements": null,
  "sort": null,
  "pageable": null,
  "first": null,
  "last": null,
  "empty": null,
} satisfies PageFavoriteResponseDto

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PageFavoriteResponseDto
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


