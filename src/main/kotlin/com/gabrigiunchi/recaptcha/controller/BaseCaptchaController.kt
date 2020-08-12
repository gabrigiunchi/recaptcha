package com.gabrigiunchi.recaptcha.controller

import com.gabrigiunchi.recaptcha.service.CaptchaValidator

open class BaseCaptchaController(private val captchaValidator: CaptchaValidator) {

    protected fun validateCaptcha(captcha: String?) {
        if (!this.captchaValidator.validateCaptcha(captcha)) {
            throw Exception("Forbidden")
        }
    }
}