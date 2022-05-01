package helios

import org.springframework.stereotype.Service

@Service
class MathUtils {
    companion object {
        fun isMultipleOf(multiple: Int, number: Int): Boolean {
            return number % multiple == 0
        }
    }
}