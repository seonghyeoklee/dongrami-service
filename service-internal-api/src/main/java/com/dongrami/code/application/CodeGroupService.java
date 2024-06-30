package com.dongrami.code.application;

import com.dongrami.code.dto.request.RequestCreateCodeGroupDto;
import com.dongrami.code.dto.request.RequestUpdateCodeGroupDto;
import com.dongrami.code.dto.response.ResponseCodeGroupDto;
import com.dongrami.code.repository.CodeGroupRepository;
import com.dongrami.code.repository.support.CodeGroupSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeGroupService {
    private final CodeGroupRepository codeGroupRepository;

    public Page<ResponseCodeGroupDto> getCodeGroupPageBySearch(Pageable pageable, CodeGroupSearchDto codeGroupSearchDto) {
        return null;
    }

    public ResponseCodeGroupDto getCodeGroupById(Long id) {
        return null;
    }

    public void createCodeGroup(RequestCreateCodeGroupDto requestCreateCodeGroupDto) {

    }

    public void updateCodeGroup(Long id, RequestUpdateCodeGroupDto requestUpdateCodeGroupDto) {

    }

    public void deleteCodeGroup(Long id) {

    }
}
