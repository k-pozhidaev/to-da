package io.poshidaev.toda.repository;

import io.poshidaev.toda.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
