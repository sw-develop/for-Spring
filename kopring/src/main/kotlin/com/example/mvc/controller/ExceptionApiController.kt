package com.example.mvc.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

import com.example.mvc.model.http.Error
import com.example.mvc.model.http.ErrorResponse
import com.example.mvc.model.http.UserRequest
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.validation.Valid

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

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(
            e: MethodArgumentNotValidException, request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach { objectError ->
            val error = Error().apply {
                val fieldError = objectError as FieldError //형변환
                this.field = fieldError.field
                this.message = objectError.defaultMessage
                this.value = objectError.rejectedValue
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

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintValidationException(
            e: ConstraintViolationException, request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()
        //1. Error List
        e.constraintViolations.forEach {
            val error = Error().apply {
                this.field = it.propertyPath.last().name
                this.message = it.message
                this.value = it.invalidValue
            }
            errors.add(error)
        }
        //2. ErrorResponse
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생했습니다."
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }
        //3. ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }


    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        println("ExceptionHandler에 의한 처리")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}