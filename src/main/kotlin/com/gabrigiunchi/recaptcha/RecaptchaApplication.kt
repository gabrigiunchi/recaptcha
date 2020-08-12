package com.gabrigiunchi.recaptcha

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RecaptchaApplication

fun main(args: Array<String>) {
    runApplication<RecaptchaApplication>(*args)
}
