package helios.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class FizzBuzzStatisticsResponse(
    @JsonProperty("int1")
    val firstMultiple: Int? = null,
    @JsonProperty("int2")
    val secondMultiple: Int? = null,
    val limit: Int? = null,
    @JsonProperty("str1")
    val firstMultipleResponse: String? = null,
    @JsonProperty("str2")
    val secondMultipleResponse: String? = null,
    val count: Int? = null
)