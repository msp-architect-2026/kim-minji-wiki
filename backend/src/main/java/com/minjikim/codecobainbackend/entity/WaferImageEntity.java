package com.minjikim.codecobainbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name= "wafer_image")

public class WaferImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String filepath;
    private LocalDateTime createdAt;

    // 분석 결과
    private String prediction;
    private Double confidence;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


}
