package helios.service

import org.springframework.stereotype.Service

@Service
class BoxManagementService {
    companion object {
        const val BOX_SIZE = 10
        const val BOX_SEPARATOR = "/"
    }

    fun findBestItemsNumberPerBox(itemsSize: String): String {
        val itemsPerBoxResult = StringBuilder()
        val itemsSizeRemaining = itemsSize.map { it.digitToInt() }
            .sortedDescending()
            .toMutableList()

        while (itemsSizeRemaining.isNotEmpty()) {
            val itemSizeInTreatment = itemsSizeRemaining.first()
            val complementarySize = BOX_SIZE - itemSizeInTreatment
            itemsPerBoxResult.append(itemSizeInTreatment)
            itemsSizeRemaining.remove(itemSizeInTreatment)

            if (itemsSizeRemaining.contains(complementarySize)) {
                itemsPerBoxResult.append(complementarySize)
                itemsSizeRemaining.remove(complementarySize)
                itemsPerBoxResult.append(BOX_SEPARATOR)
            } else {
                var notFullYet = true
                while (notFullYet) {
                    val itemsSizesLessThanComplementarySize =
                        itemsSizeRemaining.filter { itemRemaining -> itemRemaining < complementarySize }
                            .toList()
                    if (itemsSizesLessThanComplementarySize.isEmpty()) {
                        itemsPerBoxResult.append(BOX_SEPARATOR)
                        notFullYet = false
                    } else {
                        val closestComplementarySize = itemsSizesLessThanComplementarySize.maxOf { it }
                        itemsPerBoxResult.append(closestComplementarySize)
                        itemsSizeRemaining.remove(closestComplementarySize)
                    }
                }
            }
        }
        return itemsPerBoxResult.toString().dropLast(1)
    }
}