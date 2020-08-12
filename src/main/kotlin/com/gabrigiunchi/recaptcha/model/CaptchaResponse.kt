package com.gabrigiunchi.recaptcha.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


class CaptchaResponse(
        val success: Boolean,

        @JsonProperty("challenge_ts")
        val timestamp: Date?,

        val hostname: String?,

        @JsonProperty("error-codes")
        val errorCodes: List<String>? = emptyList()
)