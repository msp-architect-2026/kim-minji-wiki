package com.minjikim.codecobainbackend.dto.response;

import com.minjikim.codecobainbackend.entity.WaferImageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AiRecordResponseDto {
    private Long id;
    private String filename;
    private String filepath;
    private String prediction;
    private Double confidence;
    private LocalDateTime createdAt;

    // Entity → DTO 변환 정적 메서드
    public static AiRecordResponseDto from(WaferImageEntity entity) {
        return new AiRecordResponseDto(
                entity.getId(),
                entity.getFilename(),
                entity.getFilepath(),
                entity.getPrediction(),
                entity.getConfidence(),
                entity.getCreatedAt()
        );
    }
}
