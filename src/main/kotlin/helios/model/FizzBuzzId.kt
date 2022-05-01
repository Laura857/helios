package helios.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class FizzBuzzId(
    @Column(nullable = false)
    val firstMultiple: Int,

    @Column(nullable = false)
    val secondMultiple: Int,

    @Column(name = "request_limit", nullable = false)
    val limit: Int,

    @Column(nullable = false)
    val firstMultipleResponse: String,

    @Column(nullable = false)
    val secondMultipleResponse: String,
) : Serializable