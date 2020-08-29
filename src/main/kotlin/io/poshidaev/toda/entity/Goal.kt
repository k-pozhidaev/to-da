package io.poshidaev.toda.entity

import javax.persistence.*

@Entity
class Goal : SingleIdEntity{

    constructor(id: Long?, text: String?, type: GoalType, trialsCount: Int, topics: List<Topic>, approaches: List<GoalApproach>) : super(id) {
        this.text = text
        this.type = type
        this.trialsCount = trialsCount
        this.topics = topics
        this.approaches = approaches
    }


    constructor(id: Long?, text: String?) : super(id) {
        this.text = text
    }

    constructor(id: Long?, text: String?, type: GoalType, trialsCount: Int, topics: List<Topic>?) : super(id) {
        this.text = text
        this.type = type
        this.trialsCount = trialsCount
        this.topics = topics
    }

    @Column(nullable = false)
    var text: String? = null

    @Enumerated(EnumType.ORDINAL)
    var type: GoalType = GoalType.DAILY

    var trialsCount: Int = 1

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tasks_to_topics",
            joinColumns = [JoinColumn(name = "goal_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "topic_id", referencedColumnName = "text", columnDefinition = "text")])
    var topics: List<Topic>? = mutableListOf()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "goal")
    var approaches: List<GoalApproach>? = mutableListOf()

}