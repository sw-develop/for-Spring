package com.example.kopring_todo_list.database

data class TodoDataBase(
        var index: Int = 0,
        var todoList: MutableList<Todo> = mutableListOf()
){

    fun init() { //초기화
        this.index = 0
        this.todoList = mutableListOf()
        println("[DEBUG] todo database init")
    }
}