package helios.service

import helios.MathUtils.Companion.isMultipleOf
import helios.dto.FizzBuzzRequest
import org.springframework.stereotype.Service

@Service
class FizzBuzzService {

    fun fizzBuzz(fizzBuzzRequest: FizzBuzzRequest): List<String> {
        val result: ArrayList<String> = arrayListOf()
        for (number in 1..fizzBuzzRequest.limit) {
            result.add(buildResponse(number, fizzBuzzRequest))
        }
        return result
    }

    fun buildResponse(number: Int, fizzBuzzRequest: FizzBuzzRequest): String {
        val response: StringBuilder = StringBuilder()
        if (isMultipleOf(fizzBuzzRequest.firstMultiple, number))
            response.append(fizzBuzzRequest.firstMultipleResponse)
        if (isMultipleOf(fizzBuzzRequest.secondMultiple, number))
            response.append(fizzBuzzRequest.secondMultipleResponse)
        return response.toString().ifEmpty { number.toString() }
    }
}