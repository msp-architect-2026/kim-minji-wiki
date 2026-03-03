package com.minjikim.codecobainbackend.util;

import com.minjikim.codecobainbackend.exception.FileProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
public class ImageFileValidator {
    public void validate(MultipartFile file) {
        if (file.isEmpty()) throw new FileProcessingException("파일이 비어있습니다.");

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) throw new FileProcessingException("유효하지 않은 이미지입니다.");
        } catch (IOException e) {
            throw new FileProcessingException("이미지 판독 실패", e);
        }
    }
}
