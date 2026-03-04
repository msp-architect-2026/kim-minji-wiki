package com.minjikim.codecobainbackend.service;

import com.minjikim.codecobainbackend.dto.response.AiPredictionResponse;
import com.minjikim.codecobainbackend.exception.AiRequestSendException;
import com.minjikim.codecobainbackend.exception.AiServerException;
import com.minjikim.codecobainbackend.exception.InvalidAiResponseException;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiAnalysisService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AiAnalysisService.class);

    @Value("${ai.server.url}${ai.endpoint.predict}")
    private String aiUrl;

    public AiPredictionResponse analyze(String s3Url) {
        try {
            // 1. 요청 body 구성
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // 또는 application/x-www-form-urlencoded

            Map<String, String> body = Map.of("imageUrl", s3Url);  // AI 서버가 기대하는 파라미터 키에 맞출 것
            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            logger.info("AI 서버에 분석 요청 전송: {}", aiUrl);
            logger.info("요청 파라미터: imageUrl={}", s3Url);

            // 2. 요청 전송
            ResponseEntity<AiPredictionResponse> response = restTemplate.exchange(
                    aiUrl,
                    HttpMethod.POST,
                    request,
                    AiPredictionResponse.class
            );

            // 3. 응답 유효성 검사
            validateResponse(response);
            logger.info("AI 분석 결과: {}", response.getBody());

            return response.getBody();

        } catch (Exception e) {
            logger.error("AI 분석 요청 중 오류 발생: {}", e.getMessage(), e);
            throw new AiServerException("AI 분석 실패", e);
        }
    }

    private void validateResponse(ResponseEntity<AiPredictionResponse> response) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new AiRequestSendException("AI 서버 응답 오류: " + response.getStatusCode());
        }

        AiPredictionResponse body = response.getBody();
        if (body == null) {
            throw new InvalidAiResponseException("AI 응답 body가 null 입니다.");
        }

        if (body.getPrediction() == null || body.getPrediction().isBlank()) {
            throw new InvalidAiResponseException("AI 응답 prediction 값이 비어 있습니다.");
        }

        if (body.getConfidence() == null || body.getConfidence() < 0 || body.getConfidence() > 1) {
            throw new InvalidAiResponseException("AI 응답 confidence 값이 유효하지 않습니다.");
        }
    }
}
