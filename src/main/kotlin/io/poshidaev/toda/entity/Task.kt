package io.poshidaev.toda.entity

import javax.persistence.*

@Entity
class Task {

    constructor(text: String?){
        this.text = text
    }

    constructor()

    constructor(id: Long?, text: String?) {
        this.id = id
        this.text = text
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var text: String? = null
}