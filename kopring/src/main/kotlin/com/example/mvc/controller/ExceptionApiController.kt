package com.example.mvc.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

import com.example.mvc.model.http.Error
import com.example.mvc.model.http.ErrorResponse
import org.springframework.validation.annotation.Validated
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello() {
        val list = mutableListOf<String>()
        val temp = list[0]
    }

    @GetMapping("")
    fun get(
            @NotBlank
            @Size(min = 2, max = 5)
            @RequestParam name: String,

            @Min(10)
            @RequestParam age: Int
    ): String {
        println(name)
        println(age)
        return "$name $age"
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintValidationException(
            e: ConstraintViolationException, request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()

        e.constraintViolations.forEach {
            val error = Error().apply {
                this.field = it.propertyPath.last().name
                this.message = it.message
                this.value = it.invalidValue
            }
            errors.add(error)
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생했습니다."
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }


    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        println("ExceptionHandler에 의한 처리")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}