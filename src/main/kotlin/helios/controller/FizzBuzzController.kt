package helios.controller

import helios.service.FizzBuzzService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FizzBuzzController {
    @Autowired
    lateinit var fizzBuzService: FizzBuzzService

    @GetMapping("api/v1/fizzBuzz")
    fun fizzBuzz(@RequestParam("int1") int1: Int,
                 @RequestParam("int2") int2: Int,
                 @RequestParam("limit") limit: Int,
                 @RequestParam("str1") str1: String,
                 @RequestParam("str2") str2: String) = fizzBuzService.fizzBuzz(int1, int2, limit, str1, str2)

}