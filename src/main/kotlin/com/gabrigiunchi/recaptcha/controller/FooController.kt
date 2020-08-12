package com.gabrigiunchi.recaptcha.controller

import com.gabrigiunchi.recaptcha.model.CaptchaRequest
import com.gabrigiunchi.recaptcha.service.CaptchaValidator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class FooRequest(val id: String) : CaptchaRequest()
class FooResponse(val version: String, val status: String)

@RequestMapping("/foo")
@RestController
class FooController(captchaValidator: CaptchaValidator) : BaseCaptchaController(captchaValidator) {

    @Value("\${application.version}")
    private lateinit var version: String

    @PostMapping
    fun foo(@Validated @RequestBody request: FooRequest): ResponseEntity<FooResponse> {
        LOGGER.info("GET request with id ${request.id}")
        this.validateCaptcha(request.captchaResponse)
        return ResponseEntity.ok(FooResponse(this.version, "OK"))
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(FooController::class.java)
    }
}