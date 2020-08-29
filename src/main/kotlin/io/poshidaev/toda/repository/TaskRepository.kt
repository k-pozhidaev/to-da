package io.poshidaev.toda.repository

import io.poshidaev.toda.entity.Goal
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Goal, Long>
