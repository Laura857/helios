package helios.model

import javax.persistence.*

@Entity
data class FizzBuzz(
    @EmbeddedId
    var id: FizzBuzzId,

    @Column(name = "request_count", nullable = false)
    val count: Int,
)