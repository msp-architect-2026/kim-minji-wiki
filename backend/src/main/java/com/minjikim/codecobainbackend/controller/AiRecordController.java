package com.minjikim.codecobainbackend.controller;

import com.minjikim.codecobainbackend.dto.response.AiRecordResponseDto;
import com.minjikim.codecobainbackend.entity.WaferImageEntity;
import com.minjikim.codecobainbackend.repository.WaferImageRepository;
import com.minjikim.codecobainbackend.service.WaferImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/wafer")
@RequiredArgsConstructor

public class AiRecordController {
    private final WaferImageService waferImageService;
    private final WaferImageRepository waferImageRepository;
    private static final Logger log = LoggerFactory.getLogger(AiRecordController.class);

    @GetMapping("/records")
    public List<WaferImageEntity> getRecords() {
        log.info("분석 기록 전체 목록 조회 요청 수신");
        return waferImageService.findAll();
    }

    // 프론트용 (검색+정렬+페이지네이션)
    @GetMapping("/records/pages")
    public Page<AiRecordResponseDto> getPagedRecords(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("페이지네이션 목록 조회 요청: search='{}', page={}, size={}", search, page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<WaferImageEntity> resultPage =
                waferImageRepository.findByPredictionContainingIgnoreCaseOrderByCreatedAtDesc(search, pageable);

        return resultPage.map(AiRecordResponseDto::from);
    }

    // 프론트용 (상세 조회)
    @GetMapping("/records/{id}")
    public AiRecordResponseDto getRecordById(@PathVariable Long id) {
        log.info("상세 조회 요청: ID={}", id);
        WaferImageEntity entity = waferImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 기록을 찾을 수 없습니다: " + id));
        return AiRecordResponseDto.from(entity);
    }


}
