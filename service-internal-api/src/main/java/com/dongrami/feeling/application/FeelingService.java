package com.dongrami.feeling.application;

import com.dongrami.exception.BaseException;
import com.dongrami.exception.ErrorCode;
import com.dongrami.feeling.domain.FeelingEntity;
import com.dongrami.feeling.dto.FeelingDto;
import com.dongrami.feeling.dto.request.RequestCreateFeelingDto;
import com.dongrami.feeling.repository.FeelingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FeelingService {
    private final FeelingRepository feelingRepository;

    public List<FeelingDto> getFeelings(boolean isDeleted) {
        List<FeelingEntity> feelingEntities = feelingRepository.findAllByIsDeletedOrderByFeelingOrder(isDeleted);

        return feelingEntities.stream()
                .map(FeelingDto::from)
                .toList();
    }

    public void createFeeling(RequestCreateFeelingDto request) {
        feelingRepository.findAll()
                .forEach(feelingEntity -> {
                    if (feelingEntity.getFeelingName().equals(request.getFeelingName())) {
                        throw new BaseException(ErrorCode.FEELING_NAME_DUPLICATE);
                    }
                    if (feelingEntity.getFeelingOrder() == request.getFeelingOrder()) {
                        throw new BaseException(ErrorCode.FEELING_ORDER_DUPLICATE);
                    }
                });

        FeelingEntity entity = FeelingEntity.builder()
                .feelingName(request.getFeelingName())
                .imageUrl(request.getImageUrl())
                .feelingOrder(request.getFeelingOrder())
                .isDeleted(false)
                .build();

        feelingRepository.save(entity);
    }

}
