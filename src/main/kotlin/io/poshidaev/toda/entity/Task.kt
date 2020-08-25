package io.poshidaev.toda.entity

import javax.persistence.*

@Entity
class Task : SingleIdEntity{

    constructor(id: Long?, text: String?, type: GoalType, status: GoalStatus, trialsCount: Int, approachesCount: Int) : super(id) {
        this.text = text
        this.type = type
        this.status = status
        this.trialsCount = trialsCount
        this.approachesCount = approachesCount
    }

    constructor(id: Long?, text: String?) : super(id) {
        this.text = text
    }

    @Column(nullable = false)
    var text: String? = null

    @Enumerated(EnumType.ORDINAL)
    var type: GoalType = GoalType.DAILY

    @Enumerated(EnumType.ORDINAL)
    var status: GoalStatus = GoalStatus.IN_PROGRESS

    var trialsCount: Int = 0

    var approachesCount: Int = 0

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tasks_to_topics",
            joinColumns = [JoinColumn(name = "task_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "topic_id", referencedColumnName = "text", columnDefinition = "text")])
    var topics: List<Topic>? = mutableListOf()

}