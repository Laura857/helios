package helios.controller

import helios.service.BoxManagementService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BoxManagementController {

    @Autowired
    lateinit var boxManagementService: BoxManagementService

    @GetMapping("api/v1/itemsPerBox/{itemsSize}")
    @Operation(
        summary = "Optimize the number of items per box in order to maximize the number of boxes"
    )
    fun findBestItemsNumberPerBox(
        @PathVariable("itemsSize")
        @Schema(
            example = "1",
            description = "A string with items size"
        )
        itemsSize: String
    ) =
        boxManagementService.findBestItemsNumberPerBox(itemsSize)
}
