package com.danceschool.danceschool.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="dance_group")
public class DanceGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dance_group_id")
    private int danceGroupId;

    @Column(name = "level_id")
    private int levelId;

    @Column(name = "school_id")
    private int schoolId;

}
