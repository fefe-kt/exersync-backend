package br.com.exersync.domain.entities

import br.com.exersync.domain.enums.RoleEnum
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var email: String,
    var password: String,
    var phone: String,
    var profilePictureUrl: String?,
    var role: RoleEnum = RoleEnum.REGULAR_USER,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val workoutLists: MutableList<WorkoutListEntity> = mutableListOf()
)
