package io.jedaev.dayon.dao

import io.jedaev.dayon.model.Tables
import io.jedaev.dayon.model.tables.pojos.Task
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class TasksRepositoryImpl @Autowired constructor(val dsl: DSLContext) : TasksRepository {

    override fun findOne(id: Long): Task {
        return dsl
                .selectFrom(Tables.TASK)
                .where(Tables.TASK.ID.eq(id))
                .fetchOne{t -> Task(t.id, t.text) }
    }

    override fun findAll(): List<Task> {
        return dsl.selectFrom(Tables.TASK)
                .fetch{ t-> Task(t.id, t.text) }
    }
}