package com.minjikim.codecobainbackend.repository;

import com.minjikim.codecobainbackend.entity.WaferImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaferImageRepository extends JpaRepository<WaferImageEntity, Long> {
    Page<WaferImageEntity> findByPredictionContainingIgnoreCaseOrderByCreatedAtDesc(String keyword, Pageable pageable);

}
