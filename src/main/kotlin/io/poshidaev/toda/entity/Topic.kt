package io.poshidaev.toda.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Topic : BaseEntity {

    constructor(text: String?) : super() {
        this.text = text
    }

    constructor() : super()


    @Id
    var text: String? = null

    @ManyToMany(mappedBy = "topics", fetch = FetchType.LAZY)
    var goals: List<Goal>? = mutableListOf()
}