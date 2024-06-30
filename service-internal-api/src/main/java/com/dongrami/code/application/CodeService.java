package com.dongrami.code.application;

import com.dongrami.code.dto.request.RequestCreateCodeDto;
import com.dongrami.code.dto.request.RequestUpdateCodeDto;
import com.dongrami.code.dto.response.ResponseCodeDto;
import com.dongrami.code.repository.CodeRepository;
import com.dongrami.code.repository.support.CodeGroupSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;

    public Page<ResponseCodeDto> getCodePageBySearch(Pageable pageable, CodeGroupSearchDto codeGroupSearchDto) {
        return null;
    }

    public ResponseCodeDto getCodeById(Long id) {
        return null;
    }

    public void createCode(RequestCreateCodeDto requestCreateCodeDto) {

    }

    public void updateCode(Long id, RequestUpdateCodeDto requestUpdateCodeDto) {

    }

    public void deleteCode(Long id) {

    }
}
