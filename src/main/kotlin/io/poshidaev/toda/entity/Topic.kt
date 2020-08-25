package io.poshidaev.toda.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Topic : BaseEntity() {

    @Id
    var text: String? = null

    @ManyToMany(mappedBy = "topics", fetch = FetchType.LAZY)
    var tasks: List<Task>? = mutableListOf()
}