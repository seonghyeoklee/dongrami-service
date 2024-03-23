package com.dongrami.diary.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryTagEntities {

    @OneToMany(mappedBy = "diaryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiaryTagEntity> diaryTagEntities = new HashSet<>();

    public void add(DiaryTagEntity diaryTagEntity) {
        this.diaryTagEntities.add(diaryTagEntity);
    }

}
