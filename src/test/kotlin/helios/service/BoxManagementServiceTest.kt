package helios.service

import helios.service.BoxManagementService.Companion.BOX_SEPARATOR
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
internal class BoxManagementServiceTest {
    companion object {
        const val ONE_CHAR_9 = "9"
        const val ONE_CHAR_7 = "7"
        const val ONE_CHAR_1 = "1"
    }

    @Autowired
    lateinit var boxManagementService: BoxManagementService

    @Test
    fun `findBestItemsNumberPerBox should return empty string when input is empty string`() {
        assertThat(boxManagementService.findBestItemsNumberPerBox(String()))
            .isEqualTo(String())
    }

    @Test
    fun `findBestItemsNumberPerBox should return only one char when input only contains one char`() {
        assertThat(boxManagementService.findBestItemsNumberPerBox(ONE_CHAR_9))
            .isEqualTo(ONE_CHAR_9)
    }

    @Test
    fun `findBestItemsNumberPerBox should return result with only one box when inputs have complementary sizes`() {
        assertThat(boxManagementService.findBestItemsNumberPerBox(ONE_CHAR_1 + ONE_CHAR_9))
            .isEqualTo(ONE_CHAR_9 + ONE_CHAR_1)
    }

    @Test
    fun `findBestItemsNumberPerBox should return result with two boxes when inputs dont have complementary sizes`() {
        assertThat(boxManagementService.findBestItemsNumberPerBox(ONE_CHAR_7 + ONE_CHAR_9))
            .isEqualTo(ONE_CHAR_9 + BOX_SEPARATOR + ONE_CHAR_7)
    }

    @Test
    fun `findBestItemsNumberPerBox should return result with three boxes when inputs have four characters with one complementary size and two not complementary sizes`() {
        assertThat(boxManagementService.findBestItemsNumberPerBox(ONE_CHAR_7 + ONE_CHAR_9 + ONE_CHAR_1 + ONE_CHAR_9))
            .isEqualTo(ONE_CHAR_9 + ONE_CHAR_1 + BOX_SEPARATOR + ONE_CHAR_9 + BOX_SEPARATOR + ONE_CHAR_7)
    }
}