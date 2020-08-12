package com.gabrigiunchi.recaptcha.service

import com.gabrigiunchi.recaptcha.model.CaptchaResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class CaptchaValidator {

    @Value("\${google.recaptcha.secret}")
    private lateinit var recaptchaSecret: String

    fun validateCaptcha(captchaResponse: String?): Boolean {
        LOGGER.info("Validating captcha")
        val restTemplate = RestTemplate()
        val requestMap: MultiValueMap<String, String> = LinkedMultiValueMap()
        requestMap.add("secret", recaptchaSecret)
        requestMap.add("response", captchaResponse)
        return try {
            val success = restTemplate.postForObject(
                    GOOGLE_RECAPTCHA_ENDPOINT,
                    requestMap,
                    CaptchaResponse::class.java
            )?.success ?: false

            LOGGER.info("Captcha is valid: $success")
            success
        } catch (e: Exception) {
            LOGGER.error("Exception during validation of captcha", e)
            false
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CaptchaValidator::class.java)
        private const val GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify"
    }
}