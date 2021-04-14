package com.qbssystem.resource.oauth2

import feign.RequestInterceptor
import feign.RequestTemplate
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.client.OAuth2RestTemplate

class OAuth2FeignSystemTokenInterceptor(
    private val oAuth2RestTemplate: OAuth2RestTemplate
) : RequestInterceptor {

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    @Synchronized
    override fun apply(template: RequestTemplate) {
        val accessToken = oAuth2RestTemplate.accessToken
        LoggerFactory.getLogger(OAuth2FeignSystemTokenInterceptor::class.java).info("[DEBUG] accessToken=$accessToken")
        template.header(AUTHORIZATION_HEADER, "$TOKEN_TYPE $accessToken")
    }
}