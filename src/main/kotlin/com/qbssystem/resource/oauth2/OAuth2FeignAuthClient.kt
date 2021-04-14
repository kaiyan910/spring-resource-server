package com.qbssystem.resource.oauth2

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "auth",
    url = "http://localhost:8080",
    configuration = [OAuth2FeignSystemConfig::class]
)
interface OAuth2FeignAuthClient {

    @GetMapping("/api/v1/test")
    fun me(): String
}