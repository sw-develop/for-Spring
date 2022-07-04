package com.example.kopring_todo_list.repository

import com.example.kopring_todo_list.config.AppConfig
import com.example.kopring_todo_list.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class TodoRepositoryTest {

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before() {
        todoRepositoryImpl.todoDataBase.init()
    }

    @Test
    fun saveTest() {
        //given
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }
        //when
        val result = todoRepositoryImpl.save(todo)
        //then
        Assertions.assertEquals(1, result?.index)
        Assertions.assertEquals("테스트 일정", result?.title)
        Assertions.assertEquals("테스트", result?.description)
        Assertions.assertNotNull(result?.createdAt)
        Assertions.assertNotNull(result?.updatedAt)
    }

    @Test
    fun saveAllTest() {
        //given
        val todoList = mutableListOf(
                Todo().apply {
                    this.title = "테스트 일정1"
                    this.description = "테스트1"
                    this.schedule = LocalDateTime.now()
                },
                Todo().apply {
                    this.title = "테스트 일정2"
                    this.description = "테스트2"
                    this.schedule = LocalDateTime.now()
                },
                Todo().apply {
                    this.title = "테스트 일정3"
                    this.description = "테스트3"
                    this.schedule = LocalDateTime.now()
                }
        )
        //when
        val result = todoRepositoryImpl.saveAll(todoList)
        //then
        Assertions.assertEquals(true, result)
    }

    @Test
    fun findOneTest() {
        //given
        val todoList = mutableListOf(
                Todo().apply {
                    this.title = "테스트 일정1"
                    this.description = "테스트1"
                    this.schedule = LocalDateTime.now()
                },
                Todo().apply {
                    this.title = "테스트 일정2"
                    this.description = "테스트2"
                    this.schedule = LocalDateTime.now()
                },
                Todo().apply {
                    this.title = "테스트 일정3"
                    this.description = "테스트3"
                    this.schedule = LocalDateTime.now()
                }
        )
        todoRepositoryImpl.saveAll(todoList)
        //when
        val result = todoRepositoryImpl.findOne(2)
        //then
        Assertions.assertNotNull(result)
        Assertions.assertEquals("테스트 일정2", result?.title)
    }

    @Test
    fun updateTest() {
        //given
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }
        val insertTodo = todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply {
            this.index = insertTodo?.index
            this.title = "업데이트 일정"
            this.description = "업데이트 테스트"
            this.schedule = LocalDateTime.now()
        }
        //when
        val result = todoRepositoryImpl.save(newTodo)
        //then
        Assertions.assertNotNull(result)
        Assertions.assertEquals("업데이트 일정", result?.title)
        Assertions.assertEquals("업데이트 테스트", result?.description)
    }
}