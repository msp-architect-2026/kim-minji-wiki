package com.minjikim.codecobainbackend.controller;

import com.minjikim.codecobainbackend.dto.response.AiPredictionResponse;
import com.minjikim.codecobainbackend.exception.FileProcessingException;
import com.minjikim.codecobainbackend.service.AiAnalysisService;
import com.minjikim.codecobainbackend.service.WaferImageService;
import com.minjikim.codecobainbackend.util.ImageFileValidator;
import com.minjikim.codecobainbackend.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiPredictionController {

    private final AiAnalysisService aiAnalysisService;
    private final WaferImageService waferImageService;
    private final S3Uploader s3Uploader;
    private final ImageFileValidator imageFileValidator;

    private static final Logger log = LoggerFactory.getLogger(AiPredictionController.class);

    @PostMapping("/predict")
    public AiPredictionResponse predictImage(@RequestParam("file") MultipartFile file) {

        log.info("예측 요청 수신: 파일명 = {}", file.getOriginalFilename());

        // 1. 업로드된 파일 유효성 검사
        imageFileValidator.validate(file);
        log.info("파일 유효성 검사 통과");

        // 2. S3 업로드
        String s3Url = s3Uploader.upload(file, "wafer-images");
        log.info("S3 업로드 완료: URL = {}", s3Url);

        // 3. AI 분석
        AiPredictionResponse response = aiAnalysisService.analyze(s3Url);
        log.info("AI 분석 결과 수신: prediction = {}, confidence = {}",
                response.getPrediction(), response.getConfidence());

        // 4. DB 저장
        waferImageService.save(file.getOriginalFilename(), s3Url, response);
        log.info("분석 결과 DB 저장 완료");

        return response;

    }

    @ExceptionHandler(FileProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIOException(FileProcessingException ex) {
        return "파일 처리 중 오류가 발생했습니다: " + ex.getMessage();
    }
}
