package io.poshidaev.toda.repository

import io.poshidaev.toda.entity.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>
