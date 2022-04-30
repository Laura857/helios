package helios.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping("api/v1/start")
    fun index(): String = "Start Project";

}