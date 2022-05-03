package helios.service

import helios.dto.FizzBuzzRequest
import helios.dto.FizzBuzzStatisticsResponse
import helios.model.FizzBuzz
import helios.model.FizzBuzzId
import helios.repository.FizzBuzzRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("test")
class FizzBuzzServiceTest {
    companion object {
        const val FIRST_MULTIPLE_RESPONSE = "Fizz"
        const val SECOND_MULTIPLE_RESPONSE = "Buzz"
        const val LIMIT = 5
        const val NUMBER_15 = 15
        const val NUMBER_3 = 3
        const val NUMBER_5 = 5
        const val NUMBER_4 = 4
        const val COUNT_1 = 1
        const val COUNT_2 = 2
    }

    @Autowired
    lateinit var fizzBuzService: FizzBuzzService

    @MockBean
    lateinit var fizzBuzzRepository: FizzBuzzRepository

    @Test
    fun `buildResponse should return request number when it is not a multiple of the fizz buzz request numbers`() {
        val fizzBuzzRequest =
            FizzBuzzRequest(NUMBER_5, NUMBER_3, LIMIT, FIRST_MULTIPLE_RESPONSE, SECOND_MULTIPLE_RESPONSE)
        assertThat(fizzBuzService.buildResponse(NUMBER_4, fizzBuzzRequest))
            .isEqualTo(NUMBER_4.toString())
    }

    @Test
    fun `buildResponse should return only first multiple response when it is only the multiple of the first request number`() {
        val fizzBuzzRequest =
            FizzBuzzRequest(NUMBER_3, NUMBER_4, LIMIT, FIRST_MULTIPLE_RESPONSE, SECOND_MULTIPLE_RESPONSE)
        assertThat(fizzBuzService.buildResponse(NUMBER_15, fizzBuzzRequest))
            .isEqualTo(FIRST_MULTIPLE_RESPONSE)
    }

    @Test
    fun `buildResponse should return only second multiple response when it is only the multiple of the second request number`() {
        val fizzBuzzRequest =
            FizzBuzzRequest(NUMBER_4, NUMBER_3, LIMIT, FIRST_MULTIPLE_RESPONSE, SECOND_MULTIPLE_RESPONSE)
        assertThat(fizzBuzService.buildResponse(NUMBER_15, fizzBuzzRequest))
            .isEqualTo(SECOND_MULTIPLE_RESPONSE)
    }

    @Test
    fun `buildResponse should return first and second multiple response when it is the multiple of all request numbers`() {
        val fizzBuzzRequest =
            FizzBuzzRequest(NUMBER_5, NUMBER_3, LIMIT, FIRST_MULTIPLE_RESPONSE, SECOND_MULTIPLE_RESPONSE)
        assertThat(fizzBuzService.buildResponse(NUMBER_15, fizzBuzzRequest))
            .isEqualTo(FIRST_MULTIPLE_RESPONSE + SECOND_MULTIPLE_RESPONSE)
    }

    @Test
    fun `fizzBuzz should return empty list when limit request is 0`() {
        val fizzBuzzRequest =
            FizzBuzzRequest(NUMBER_5, NUMBER_3, 0, FIRST_MULTIPLE_RESPONSE, SECOND_MULTIPLE_RESPONSE)
        assertThat(fizzBuzService.fizzBuzz(fizzBuzzRequest))
            .isEmpty()
    }

    @Test
    fun `fizzBuzz should return response when limit request is 5`() {
        val fizzBuzzRequest =
            FizzBuzzRequest(NUMBER_3, NUMBER_5, LIMIT, FIRST_MULTIPLE_RESPONSE, SECOND_MULTIPLE_RESPONSE)
        assertThat(fizzBuzService.fizzBuzz(fizzBuzzRequest))
            .isEqualTo(listOf("1", "2", FIRST_MULTIPLE_RESPONSE, NUMBER_4.toString(), SECOND_MULTIPLE_RESPONSE))
    }

    @Test
    fun `fizzBuzzStatistics should return all fields to null when no request is saved`() {
        assertThat(fizzBuzService.fizzBuzzStatistics())
            .isEqualTo(FizzBuzzStatisticsResponse())
    }

    @Test
    fun `fizzBuzzStatistics should return fields with values of the most used request when requests are saved`() {
        `when`(fizzBuzzRepository.findMostUsedRequest())
            .thenReturn(
                FizzBuzz(
                    FizzBuzzId(
                        NUMBER_5,
                        NUMBER_3,
                        LIMIT,
                        FIRST_MULTIPLE_RESPONSE,
                        SECOND_MULTIPLE_RESPONSE
                    ), NUMBER_15
                )
            )

        assertThat(fizzBuzService.fizzBuzzStatistics())
            .isEqualTo(
                FizzBuzzStatisticsResponse(
                    NUMBER_5,
                    NUMBER_3,
                    LIMIT,
                    FIRST_MULTIPLE_RESPONSE,
                    SECOND_MULTIPLE_RESPONSE,
                    NUMBER_15
                )
            )
    }

    @Test
    fun `buildFizzBuzz should return Fizzbuzz object with count equal to count in input`() {
        val fizzBuzzRequest = FizzBuzzRequest(
            NUMBER_5,
            NUMBER_3,
            LIMIT,
            FIRST_MULTIPLE_RESPONSE,
            SECOND_MULTIPLE_RESPONSE
        )
        val fizzBuzz = FizzBuzz(
            FizzBuzzId(
                NUMBER_5,
                NUMBER_3,
                LIMIT,
                FIRST_MULTIPLE_RESPONSE,
                SECOND_MULTIPLE_RESPONSE,
            ), NUMBER_15
        )
        assertThat(fizzBuzService.buildFizzBuzz(fizzBuzzRequest, NUMBER_15))
            .isEqualTo(fizzBuzz)
    }

    @Test
    fun `saveFizzBuzzRequest should save fizz buzz with count equal to 1 when no fizz buzz exist with request value`() {
        val fizzBuzzRequest = FizzBuzzRequest(
            NUMBER_5,
            NUMBER_3,
            LIMIT,
            FIRST_MULTIPLE_RESPONSE,
            SECOND_MULTIPLE_RESPONSE
        )
        val fizzBuzz = FizzBuzz(
            FizzBuzzId(
                NUMBER_5,
                NUMBER_3,
                LIMIT,
                FIRST_MULTIPLE_RESPONSE,
                SECOND_MULTIPLE_RESPONSE,
            ), COUNT_1
        )
        assertDoesNotThrow { fizzBuzService.saveFizzBuzzRequest(fizzBuzzRequest) }

        verify(fizzBuzzRepository).save(fizzBuzz)
    }

    @Test
    fun `saveFizzBuzzRequest should save fizz buzz with count equal to 2 when fizz buzz already exist with count equal to 1`() {
        val fizzBuzzRequest = FizzBuzzRequest(
            NUMBER_5,
            NUMBER_3,
            LIMIT,
            FIRST_MULTIPLE_RESPONSE,
            SECOND_MULTIPLE_RESPONSE
        )
        val fizzBuzzSaved = FizzBuzz(
            FizzBuzzId(
                NUMBER_5,
                NUMBER_3,
                LIMIT,
                FIRST_MULTIPLE_RESPONSE,
                SECOND_MULTIPLE_RESPONSE,
            ), COUNT_1
        )

        val fizzBuzzUpdate = FizzBuzz(
            FizzBuzzId(
                NUMBER_5,
                NUMBER_3,
                LIMIT,
                FIRST_MULTIPLE_RESPONSE,
                SECOND_MULTIPLE_RESPONSE,
            ), COUNT_2
        )

        `when`(
            fizzBuzzRepository.findByRequestsField(
                fizzBuzzRequest.firstMultiple,
                fizzBuzzRequest.secondMultiple,
                fizzBuzzRequest.limit,
                fizzBuzzRequest.firstMultipleResponse,
                fizzBuzzRequest.secondMultipleResponse
            )
        )
            .thenReturn(fizzBuzzSaved)

        assertDoesNotThrow { fizzBuzService.saveFizzBuzzRequest(fizzBuzzRequest) }

        verify(fizzBuzzRepository).save(fizzBuzzUpdate)
    }
}

