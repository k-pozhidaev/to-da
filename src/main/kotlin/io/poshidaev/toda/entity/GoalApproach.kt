package io.poshidaev.toda.entity

import java.util.*
import javax.persistence.*

@Entity
class GoalApproach : SingleIdEntity {
    constructor(id: Long?, status: GoalStatus, date: Date, goal: Goal?) : super(id) {
        this.status = status
        this.date = date
        this.goal = goal
    }

    constructor() : super()

    @Enumerated(EnumType.ORDINAL)
    var status: GoalStatus = GoalStatus.IN_PROGRESS

    @Temporal(value = TemporalType.DATE)
    var date: Date = Date()

    @ManyToOne(fetch = FetchType.LAZY)
    var goal: Goal? = null

}