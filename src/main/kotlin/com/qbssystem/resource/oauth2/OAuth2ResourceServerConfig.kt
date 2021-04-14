package com.qbssystem.resource.oauth2

import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import java.io.IOException
import java.nio.charset.StandardCharsets

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
class OAuth2ResourceServerConfig : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        super.configure(http)
    }

    override fun configure(resources: ResourceServerSecurityConfigurer) {

        resources
            .tokenServices(
                DefaultTokenServices()
                    .apply { setTokenStore(tokenStore()) }
            )
    }

    @Bean
    fun tokenStore(): TokenStore {

        return JwtTokenStore(jwtAccessTokenConverter())
    }

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {

        return JwtAccessTokenConverter()
            .apply { setVerifierKey(publicKey()) }
    }

    private fun publicKey(): String {

        return try {

            IOUtils.toString(
                ClassPathResource("public-key").inputStream,
                StandardCharsets.UTF_8
            )

        } catch (e: IOException) {

            throw RuntimeException(e)
        }
    }
}