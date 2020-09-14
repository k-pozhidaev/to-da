package io.poshidaev.toda.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class GoalApproach extends SingleIdEntity{

    @Enumerated(EnumType.ORDINAL)
    private GoalStatus status;

    @Temporal(value = TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Goal goal;
}
