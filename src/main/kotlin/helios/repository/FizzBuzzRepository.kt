package helios.repository

import helios.model.FizzBuzz
import helios.model.FizzBuzzId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FizzBuzzRepository : CrudRepository<FizzBuzz, FizzBuzzId> {
    @Query(
        "SELECT * " +
                "FROM fizz_buzz " +
                "ORDER BY request_count DESC " +
                "limit 1", nativeQuery = true
    )
    fun findMostUsedRequest(): FizzBuzz?

    @Query(
        "SELECT * FROM fizz_buzz " +
                "WHERE first_multiple = :firstMultiple " +
                "AND second_multiple = :secondMultiple " +
                "AND request_limit = :limit " +
                "AND first_multiple_response = :firstMultipleResponse " +
                "AND second_multiple_response = :secondMultipleResponse",
        nativeQuery = true
    )
    fun findByRequestsField(
        firstMultiple: Int,
        secondMultiple: Int,
        limit: Int,
        firstMultipleResponse: String,
        secondMultipleResponse: String
    ): FizzBuzz?
}