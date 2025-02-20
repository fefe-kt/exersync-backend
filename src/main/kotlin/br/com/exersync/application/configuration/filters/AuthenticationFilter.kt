package br.com.exersync.application.configuration.filters

import br.com.exersync.services.UserService
import br.com.exersync.services.UserTokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthenticationFilter(
    private val userService: UserService,
    private val userTokenService: UserTokenService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.getHeader("Authorization")?.getBearerToken()?.let { token ->
            userTokenService.getSubject(token)?.let { subject ->
                if (SecurityContextHolder.getContext().authentication == null) {
                    val user = userService.loadUserByUsername(subject)

                    if (userTokenService.isValid(token, user))
                        updateContext(user, request)
                }
            }
        }
        filterChain.doFilter(request, response)
    }

    fun String.getBearerToken(): String? = this.takeIf { it.startsWith("Bearer ") }?.substringAfter("Bearer ")

    fun updateContext(user: UserDetails, request: HttpServletRequest) {
        val token = UsernamePasswordAuthenticationToken(user, null, user.authorities)
        token.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = token
    }
}