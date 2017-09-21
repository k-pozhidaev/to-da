package io.jedaev.dayon.dao

import io.jedaev.dayon.model.tables.pojos.Task

interface TasksRepository {

    fun findOne(id:Long): Task
    fun findAll():List<Task>

}