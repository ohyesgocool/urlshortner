package model

import jakarta.persistence.Column
import jakarta.persistence.Id

data class Url
    (
    @Id
    val id: Long = 0L,
    @Column(name = "long_url", nullable = true)
    var longUrl: String
)