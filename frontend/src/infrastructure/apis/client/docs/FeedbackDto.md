
# FeedbackDto


## Properties

Name | Type
------------ | -------------
`rating` | number
`userType` | string
`message` | string
`feedbackOptionIds` | Set&lt;number&gt;

## Example

```typescript
import type { FeedbackDto } from ''

// TODO: Update the object below with actual values
const example = {
  "rating": 5,
  "userType": BUYER,
  "message": This is a very cool website!,
  "feedbackOptionIds": [1,2,4],
} satisfies FeedbackDto

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as FeedbackDto
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


