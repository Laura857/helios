package helios.repository

import helios.model.FizzBuzz
import helios.model.FizzBuzzId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FizzBuzzRepository : CrudRepository<FizzBuzz, FizzBuzzId> {
    @Query("SELECT * FROM fizz_buzz", nativeQuery = true)
    fun findMostUsedRequest(): FizzBuzz?
}