package io.poshidaev.toda.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class SingleIdEntity : BaseEntity {

    constructor() : super()

    constructor(id: Long?) : super() {
        this.id = id
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
}