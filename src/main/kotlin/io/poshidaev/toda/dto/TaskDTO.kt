package io.poshidaev.toda.dto

import io.poshidaev.toda.entity.GoalStatus
import io.poshidaev.toda.entity.GoalType
import io.poshidaev.toda.entity.Task

data class TaskDTO (val id: Long, val text: String, val type: GoalType, val status: GoalStatus, val  trialsCount: Int, val  approachesCount: Int){
    fun toTask() : Task {
        return Task(id, text, type, status, trialsCount, approachesCount)
    }
}
