package io.poshidaev.toda.repository;

import io.poshidaev.toda.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {

    @Query(value = """
SELECT t.text
FROM topic t
left join tasks_to_topics ttt on t.text = ttt.topic_id
group by t.text
order by count(ttt.topic_id) DESC
""", nativeQuery = true)
    List<String> getPopularTen();
}
