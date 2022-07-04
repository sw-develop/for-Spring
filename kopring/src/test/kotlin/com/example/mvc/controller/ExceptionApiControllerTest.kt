package com.example.mvc.controller

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
                get("/api/exception/hello")
        ).andExpect(
                status().isBadRequest
        ).andExpect(
                content().string("hello")
        )
                .andDo(print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "pooh")
        queryParams.add("age", "12")

        mockMvc.perform(
                get("/api/exception").queryParams(queryParams)
        ).andExpect(
                status().isOk
        ).andExpect(
                content().string("pooh 12")
        )
    }

    @Test
    fun getFailTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "pooh")
        queryParams.add("age", "1")

        mockMvc.perform(
                get("/api/exception").queryParams(queryParams)
        ).andExpect(
                status().isBadRequest
        ).andExpect(
                content().contentType("application/json")
        ).andExpect(
                jsonPath("\$.resultCode").value("FAIL")
        )
                .andDo(print())
    }

    @Test
    fun postTest() {
        //given
        val userRequest = UserRequest().apply {
            this.name = "pooh"
            this.age = 12
            this.phoneNumber = "010-1234-1234"
            this.address = "서울"
            this.email = "pooh@gmail.com"
            this.createdAt = "2022-07-04 17:00:00"
        }
        val requestBody = jacksonObjectMapper().writeValueAsString(userRequest)
        //when, then
        mockMvc.perform(
                post("/api/exception")
                        .content(requestBody)
                        .contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                status().isOk
        ).andDo(print())
    }
}