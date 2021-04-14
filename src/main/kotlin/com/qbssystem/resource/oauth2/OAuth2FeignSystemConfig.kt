package com.qbssystem.resource.oauth2

import feign.Logger
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.client.OAuth2RestTemplate

class OAuth2FeignSystemConfig {

    @Bean
    fun feignInternalClientInterceptor(restTemplate: OAuth2RestTemplate): OAuth2FeignSystemTokenInterceptor {
        return OAuth2FeignSystemTokenInterceptor(restTemplate)
    }

    @Bean
    fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }
}