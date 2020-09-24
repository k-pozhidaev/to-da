package io.poshidaev.toda.repository;

import io.poshidaev.toda.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Query("SELECT g FROM Goal AS g LEFT JOIN FETCH g.topics")
    List<Goal> getAllWithTopics();
}
