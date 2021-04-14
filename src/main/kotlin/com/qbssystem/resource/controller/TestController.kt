package com.qbssystem.resource.controller

import com.qbssystem.resource.oauth2.OAuth2FeignAuthClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1/test")
class TestController {

    @Autowired
    lateinit var oAuth2FeignAuthClient: OAuth2FeignAuthClient

    @GetMapping
    fun me(principal: Principal): Principal {
        return principal
    }

    @GetMapping("/system")
    fun me(): Map<String, String> {
        return mapOf("name" to oAuth2FeignAuthClient.me())
    }

    @GetMapping("/hello")
    @PreAuthorize("#oauth2.hasScope('secure')")
    fun hello(oAuth2Authentication: OAuth2Authentication): String {
        return "Hello World"
    }

    @GetMapping("/sing")
    @PreAuthorize("#oauth2.hasScope('all')")
    fun sing(oAuth2Authentication: OAuth2Authentication): OAuth2Authentication {
        return oAuth2Authentication
    }
}