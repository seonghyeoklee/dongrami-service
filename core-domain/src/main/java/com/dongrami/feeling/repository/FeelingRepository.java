package com.dongrami.feeling.repository;

import com.dongrami.feeling.domain.FeelingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeelingRepository extends JpaRepository<FeelingEntity, Long> {

    List<FeelingEntity> findAllByIsDeletedOrderByFeelingOrder(boolean isDeleted);

}
