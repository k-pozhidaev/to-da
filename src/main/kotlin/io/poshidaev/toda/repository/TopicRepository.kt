package io.poshidaev.toda.repository

import io.poshidaev.toda.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, String> {
}