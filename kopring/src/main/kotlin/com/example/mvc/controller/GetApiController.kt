package com.example.mvc.controller

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello world!"
    }

    @GetMapping("/get-mapping/query-param") //?name=pooh&age=1
    fun queryParam(
            @RequestParam(value = "name") name: String,
            @RequestParam(value = "age") age: Int
    ): String {
        return "$name $age"
    }

    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(
        userRequest: UserRequest
    ): UserRequest {
        println(userRequest)
        return userRequest
    }
}