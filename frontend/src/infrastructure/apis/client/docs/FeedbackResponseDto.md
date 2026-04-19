
# FeedbackResponseDto


## Properties

Name | Type
------------ | -------------
`rating` | number
`userType` | string
`message` | string
`feedbackOptions` | Set&lt;number&gt;
`createdAt` | Date
`userId` | string

## Example

```typescript
import type { FeedbackResponseDto } from ''

// TODO: Update the object below with actual values
const example = {
  "rating": null,
  "userType": null,
  "message": null,
  "feedbackOptions": null,
  "createdAt": null,
  "userId": null,
} satisfies FeedbackResponseDto

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as FeedbackResponseDto
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


