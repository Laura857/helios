package helios.controller

import helios.service.BoxManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BoxManagementController {

    @Autowired
    lateinit var boxManagementService: BoxManagementService

    @GetMapping("api/v1/itemsPerBox/{itemsSize}")
    fun findBestItemsNumberPerBox(@PathVariable("itemsSize") itemsSize: String) =
        boxManagementService.findBestItemsNumberPerBox(itemsSize)
}
