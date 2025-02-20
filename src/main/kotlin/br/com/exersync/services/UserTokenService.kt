package br.com.exersync.services

import br.com.exersync.application.configuration.properties.JwtProperties
import br.com.exersync.domain.entities.RefreshTokenEntity
import br.com.exersync.domain.entities.UserEntity
import br.com.exersync.domain.repositories.RefreshTokenRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserTokenService(
    jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    private val secretKey = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray())

    private val expirationDate: Date =
        Date(System.currentTimeMillis() + jwtProperties.expiration)

    private val refreshableTokenExpirationDate: Date =
        Date(System.currentTimeMillis() + jwtProperties.refreshTime)

    fun setRefreshToken(refreshToken: String, userEntity: UserEntity) {
        val entity = refreshTokenRepository.getRefereceByUser(userEntity)?.apply {
            this.refreshToken = refreshToken
        } ?: RefreshTokenEntity(refreshToken = refreshToken, user = userEntity)

        refreshTokenRepository.save(entity)
    }

    fun generateTokens(user: UserDetails): Pair<String, String> = Jwts.builder()
        .claims()
        .subject(user.username)
        .issuedAt(Date(System.currentTimeMillis()))
        .expiration(expirationDate)
        .and()
        .signWith(secretKey)
        .compact() to
            Jwts.builder()
                .claims()
                .subject(user.username)
                .issuedAt(Date(System.currentTimeMillis()))
                .expiration(refreshableTokenExpirationDate)
                .and()
                .signWith(secretKey)
                .compact()

    fun isValid(token: String, userDetails: UserDetails): Boolean =
        getSubject(token)?.let { subject ->
            userDetails.username == subject && !isExpired(token)
        } == true

    fun getSubject(token: String): String? =
        token.getAllClaims().subject

    fun isExpired(token: String): Boolean =
        token.getAllClaims().expiration.before(Date(System.currentTimeMillis()))

    private fun String.getAllClaims(): Claims =
        Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(this)
            .payload
}