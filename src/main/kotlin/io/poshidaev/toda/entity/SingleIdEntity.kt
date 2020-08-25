package io.poshidaev.toda.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class SingleIdEntity(@Id
                              @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?) : BaseEntity() {

}