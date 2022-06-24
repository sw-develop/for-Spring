package com.example.mvc.model.http

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

/*{
    "result": {
    "resultCode": "OK",
    "resultMessage": "성공"
},
    "description": "~~~",
    "users": [
    {
        "name": "tester",
        "age": 1,
        "email": "",
        "phoneNumber": ""
    },
    ...
    ]
}*/

data class UserResponse(
        var result: Result? = null,
        var description: String? = null,
        var users: MutableList<UserRequest>? = null
)

data class Result(
        var resultCode: String? = null,
        var resultMessage: String? = null
)