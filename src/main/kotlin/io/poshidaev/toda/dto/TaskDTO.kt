package io.poshidaev.toda.dto

import io.poshidaev.toda.entity.*

data class TaskDTO (
        val id: Long,
        val text: String,
        val type: GoalType,
        val trialsCount: Int,
        val topics: List<TopicDTO>
){
    fun toTask() : Goal {
        return Goal(id, text, type, trialsCount, topics.map { Topic(it.text) })
    }
}
