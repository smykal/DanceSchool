package com.danceschool.danceschool.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dance_group")
public class DanceGroup {

    @Id
    @Column(name = "dance_group_id")
    private final int danceGroupId;

    @Column(name = "level_id")
    private final int levelId;

    @Column(name = "school_id")
    private final int schoolId;

    public DanceGroup(int danceGroupId, int levelId, int schoolId) {
        this.danceGroupId = danceGroupId;
        this.levelId = levelId;
        this.schoolId = schoolId;
    }
}
