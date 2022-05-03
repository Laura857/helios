package helios.controller

import helios.dto.FizzBuzzRequest
import helios.service.FizzBuzzService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FizzBuzzController {
    @Autowired
    lateinit var fizzBuzService: FizzBuzzService

    @GetMapping("api/v1/fizzBuzz")
    @Operation(
        summary = "Return all number from 1 to limit by replacing all multiple of int1 by str1 " +
                "and all multiple of int2 by str2 " +
                "and all multiple of int1 and int2 by str1str2"
    )
    fun fizzBuzz(
        @RequestParam("int1") @Schema(example = "3") int1: Int,
        @RequestParam("int2") @Schema(example = "5") int2: Int,
        @RequestParam("limit") @Schema(example = "100") limit: Int,
        @RequestParam("str1") @Schema(example = "Fizz") str1: String,
        @RequestParam("str2") @Schema(example = "Buzz") str2: String
    ) =
        fizzBuzService.fizzBuzz(FizzBuzzRequest(int1, int2, limit, str1, str2))

    @GetMapping("api/v1/fizzBuzz/statistics")
    @Operation(
        summary = "Return the parameters corresponding to the most frequent request " +
                "and the number of hits for this request"
    )
    fun fizzBuzzStatistics() = fizzBuzService.fizzBuzzStatistics()
}