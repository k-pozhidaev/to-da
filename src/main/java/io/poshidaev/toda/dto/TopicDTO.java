package io.poshidaev.toda.dto;

import io.poshidaev.toda.entity.Topic;
import lombok.Data;


@Data
public class TopicDTO {
    private String text;

    public TopicDTO(String text) {
        this.text = text;
    }

    public Topic toEntity(){
        return new Topic(text);
    }

}
