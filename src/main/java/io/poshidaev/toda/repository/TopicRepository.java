package io.poshidaev.toda.repository;

import io.poshidaev.toda.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> {
}
