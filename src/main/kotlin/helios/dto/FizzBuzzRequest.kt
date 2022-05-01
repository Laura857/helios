package helios.dto

data class FizzBuzzRequest(val firstMultiple: Int,
                           val secondMultiple: Int,
                           val limit: Int,
                           val firstMultipleResponse: String,
                           val secondMultipleResponse: String)