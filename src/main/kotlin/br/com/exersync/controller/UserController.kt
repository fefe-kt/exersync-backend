package br.com.exersync.controller

import br.com.exersync.dto.mappers.toEntity
import br.com.exersync.dto.mappers.toResponse
import br.com.exersync.dto.request.UserRequest
import br.com.exersync.dto.response.UserResponse
import br.com.exersync.services.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/v1")
class UserController(private val service: UserService) {
    @PostMapping("/create")
    fun createUser(@RequestBody user: UserRequest) : UserResponse {
        return service.createUser(user.toEntity()).toResponse()
    }
    
}