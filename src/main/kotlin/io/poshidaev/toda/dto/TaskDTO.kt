package io.poshidaev.toda.dto

import io.poshidaev.toda.entity.Task

data class TaskDTO (val id: Long, val text: String){
    fun toTask() : Task {
        return Task(id, text)
    }
}
