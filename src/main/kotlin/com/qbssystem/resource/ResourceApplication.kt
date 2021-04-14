package com.qbssystem.resource

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ResourceApplication

fun main(args: Array<String>) {
    runApplication<ResourceApplication>(*args)
}
