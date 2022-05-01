package helios.service

import helios.MathUtils.Companion.isMultipleOf
import helios.dto.FizzBuzzRequest
import helios.dto.FizzBuzzStatisticsResponse
import helios.model.FizzBuzz
import helios.model.FizzBuzzId
import helios.repository.FizzBuzzRepository
import org.springframework.stereotype.Service

@Service
class FizzBuzzService(val fizzBuzzRepository: FizzBuzzRepository) {
    fun fizzBuzz(fizzBuzzRequest: FizzBuzzRequest): List<String> {
        val result: ArrayList<String> = arrayListOf()
        for (number in 1..fizzBuzzRequest.limit) {
            result.add(buildResponse(number, fizzBuzzRequest))
        }
        saveFizzBuzzRequest(fizzBuzzRequest)
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

    fun buildFizzBuzz(fizzBuzzRequest: FizzBuzzRequest, count: Int): FizzBuzz {
        return FizzBuzz(
            FizzBuzzId(
                fizzBuzzRequest.firstMultiple,
                fizzBuzzRequest.secondMultiple,
                fizzBuzzRequest.limit,
                fizzBuzzRequest.firstMultipleResponse,
                fizzBuzzRequest.secondMultipleResponse
            ), count
        )
    }

    fun saveFizzBuzzRequest(fizzBuzzRequest: FizzBuzzRequest) {
        val fizzBuzzRequestFound = fizzBuzzRepository.findByRequestsField(
            fizzBuzzRequest.firstMultiple,
            fizzBuzzRequest.secondMultiple,
            fizzBuzzRequest.limit,
            fizzBuzzRequest.firstMultipleResponse,
            fizzBuzzRequest.secondMultipleResponse
        )
        if (fizzBuzzRequestFound == null) {
            fizzBuzzRepository.save(buildFizzBuzz(fizzBuzzRequest, 1))
        } else {
            fizzBuzzRepository.save(buildFizzBuzz(fizzBuzzRequest, fizzBuzzRequestFound.count + 1))
        }
    }

    fun fizzBuzzStatistics(): FizzBuzzStatisticsResponse {
        val mostUsedRequest = fizzBuzzRepository.findMostUsedRequest()
            ?: return FizzBuzzStatisticsResponse()
        return FizzBuzzStatisticsResponse(
            mostUsedRequest.id.firstMultiple,
            mostUsedRequest.id.secondMultiple,
            mostUsedRequest.id.limit,
            mostUsedRequest.id.firstMultipleResponse,
            mostUsedRequest.id.secondMultipleResponse,
            mostUsedRequest.count
        )
    }
}