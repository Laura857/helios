package helios.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request object for fizz buzz")
data class FizzBuzzRequest(
    @field:Schema(
        description = "The first multiple",
        example = "3"
    )
    val firstMultiple: Int,

    @field:Schema(
        description = "The second multiple",
        example = "5"
    )
    val secondMultiple: Int,

    @field:Schema(
        description = "The limit",
        example = "100"
    )
    val limit: Int,

    @field:Schema(
        description = "The first string that replaces all multiple numbers of the first input number",
        example = "Fizz"
    )
    val firstMultipleResponse: String,

    @field:Schema(
        description = "The second string that replaces all multiple numbers of the second input number",
        example = "Buzz"
    )
    val secondMultipleResponse: String
)