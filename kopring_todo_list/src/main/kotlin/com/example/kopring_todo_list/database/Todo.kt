package com.example.kopring_todo_list.database

import java.time.LocalDateTime

data class Todo(
        var index: Int? = null,
        var title: String? = null,
        var description: String? = null,
        var schedule: LocalDateTime? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
)
