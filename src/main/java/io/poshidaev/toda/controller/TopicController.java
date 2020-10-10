package io.poshidaev.toda.controller;


import io.poshidaev.toda.dto.TopicDTO;
import io.poshidaev.toda.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.util.Optional;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private TopicRepository topicRepository;
    private Scheduler jdbcScheduler;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Autowired
    public void setJdbcScheduler(Scheduler jdbcScheduler) {
        this.jdbcScheduler = jdbcScheduler;
    }

    @GetMapping(value = {"/top", "/top/{text}"})
    public Flux<TopicDTO> getTopTopics(@PathVariable Optional<String> text){
        return Flux.defer( () ->
                Flux.fromIterable(topicRepository.getPopular(text.map(s -> s + "%").orElse("%"), PageRequest.of(0, 10)))
                        .subscribeOn(jdbcScheduler)
                        .map(topic -> new TopicDTO(topic.getText()))
        );
    }
}
