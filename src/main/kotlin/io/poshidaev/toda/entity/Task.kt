package io.poshidaev.toda.entity

import javax.persistence.*

@Entity
class Task : BaseEntity{

    constructor(text: String?){
        this.text = text
    }

    constructor()

    constructor(id: Long?, text: String?) {
        this.id = id
        this.text = text
    }


    @Column(nullable = false)
    var text: String? = null
}