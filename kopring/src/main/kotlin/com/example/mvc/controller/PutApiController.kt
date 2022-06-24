package com.example.mvc.controller

import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import com.example.mvc.model.http.Result
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping/object")
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        //result, description, user mutable list
        return UserResponse().apply {
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            this.description = "~~~~"
        }.apply {
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            this.users = userList
        }
    }
}