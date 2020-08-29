package io.poshidaev.toda.dto

import io.poshidaev.toda.entity.Topic

class TopicDTO {

    var text: String? = null

    fun toEntity() : Topic {
        return Topic(this.text)
    }
}