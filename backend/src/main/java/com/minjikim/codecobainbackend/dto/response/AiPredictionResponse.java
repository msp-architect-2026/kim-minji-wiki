package com.minjikim.codecobainbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 연결된 다른 코드 수정 필요
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AiPredictionResponse{
    private String prediction;
    private Double confidence;
}


