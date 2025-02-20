package br.com.exersync.services

import br.com.exersync.domain.entities.UserEntity
import br.com.exersync.domain.repositories.UserRepository
import br.com.exersync.dto.mappers.toUserDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import br.com.exersync.dto.exceptions.UserFieldAlreadyExists as UserException

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) :
    UserDetailsService {
    fun createUser(userEntity: UserEntity): UserEntity {
        isUserValid(userEntity)
        return userRepository.save(userEntity.copy(password = passwordEncoder.encode(userEntity.password)))
    }

    fun findByEmail(email: String): UserEntity? = userRepository.findByEmail(email)

    private fun isUserValid(userEntity: UserEntity) =
        if (userRepository.existsByEmail(userEntity.email))
            throw UserException.EmailAlreadyExists()
        else if (userRepository.existsByUserName(userEntity.userName))
            throw UserException.UsernameAlreadyExists() else Unit

    override fun loadUserByUsername(username: String?): UserDetails =
        userRepository.findByEmail(username)?.toUserDetails()
            ?: throw UsernameNotFoundException("O email não foi encontrado")

}