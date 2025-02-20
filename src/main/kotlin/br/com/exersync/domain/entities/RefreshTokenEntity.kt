package br.com.exersync.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "refresh_token")
data class RefreshTokenEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var refreshToken: String = "",

    @OneToOne(cascade = [CascadeType.REMOVE], orphanRemoval = true, fetch = FetchType.EAGER)
    val user: UserEntity
)