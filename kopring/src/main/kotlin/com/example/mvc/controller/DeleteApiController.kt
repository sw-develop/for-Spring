package com.example.mvc.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
            @RequestParam(name = "name") _name: String,
            @RequestParam(name = "age") _age: Int
    ): String {
        println(_name)
        println(_age)
        return "$_name  $_age"
    }
}