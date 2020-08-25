package io.poshidaev.toda.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/topic"])
class TopicController {

    @GetMapping
    fun getAll(){

    }
}