package br.com.exersync.domain.entities

import br.com.exersync.domain.enums.RoleEnum
import jakarta.persistence.*

@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["userName", "email"])])
data class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    @Column(unique = true)
    var userName: String,
    @Column(unique = true)
    var email: String,
    var password: String,
    var phone: String? = null,
    var profilePictureUrl: String? = null,
    var role: RoleEnum = RoleEnum.REGULAR_USER,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val workoutLists: MutableList<WorkoutListEntity> = mutableListOf()
)
