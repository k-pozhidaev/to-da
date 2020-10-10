package io.poshidaev.toda.controller;


import io.poshidaev.toda.dto.TopicDTO;
import io.poshidaev.toda.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

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

    @GetMapping(value = {"/top", "/top/:text"})
    public Flux<TopicDTO> getTopTopics(){
        return Flux.defer( () ->
                Flux.fromIterable(topicRepository.getPopularTen())
                        .subscribeOn(jdbcScheduler)
                        .map(TopicDTO::new)
        );
    }
}
