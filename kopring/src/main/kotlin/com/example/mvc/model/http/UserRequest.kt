package com.example.mvc.model.http

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

data class UserRequest(

        @field:NotEmpty
        @field:Size(min = 2, max = 8)
        var name: String? = null,

        @field:PositiveOrZero
        var age: Int? = null,

        @field:Email
        var email: String? = null,

        @field:NotBlank
        var address: String? = null,

        @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")
        var phoneNumber: String? = null,

        var createdAt: String? = null //yyyy-MM-dd HH:mm:ss
) {
        @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 여야 합니다.")
        private fun isValidCreatedAt(): Boolean {
                return try {
                        LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        true
                } catch (e: java.lang.Exception) {
                        false
                }
        }
}
