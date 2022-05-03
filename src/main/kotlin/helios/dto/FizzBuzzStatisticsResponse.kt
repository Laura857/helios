package helios.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Response with the most used request and the number of hits for this request")
data class FizzBuzzStatisticsResponse(
    @JsonProperty("int1")
    @field:Schema(
        description = "The first multiple",
        example = "buzz"
    )
    val firstMultiple: Int? = null,

    @JsonProperty("int2")
    @field:Schema(
        description = "The second multiple",
        example = "buzz"
    )
    val secondMultiple: Int? = null,

    @field:Schema(
        description = "The limit",
        example = "buzz"
    )val limit: Int? = null,

    @JsonProperty("str1")
    @field:Schema(
        description = "The first string that replaces all multiple numbers of the first input number",
        example = "buzz"
    )
    val firstMultipleResponse: String? = null,

    @JsonProperty("str2")
    @field:Schema(
        description = "The second string that replaces all multiple numbers of the second input number",
        example = "buzz"
    )
    val secondMultipleResponse: String? = null,

    @field:Schema(
        description = "The number of hits for the request",
        example = "buzz"
    )
    val count: Int? = null
)