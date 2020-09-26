package io.poshidaev.toda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalApproach extends SingleIdEntity{

    @Temporal(value = TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Goal goal;
}
