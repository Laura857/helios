package helios

import helios.MathUtils.Companion.isMultipleOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MathUtilsTest {
    @Test
    fun `3 is multiple of 15 should return true`() {
        assertThat(isMultipleOf(3, 15))
                .isTrue
    }

    @Test
    fun `2 is multiple of 15 should return false`() {
        assertThat(isMultipleOf(15, 2))
                .isFalse
    }
}