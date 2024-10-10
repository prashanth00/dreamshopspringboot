package com.wipro.dream_shops.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wipro.dream_shops.dto.ImageDto;
import com.wipro.dream_shops.model.Image;

public interface IImageService {
	Image getImageById(Long id);
	void deleteImageById(Long id);
	Image getImageByName(MultipartFile file,Long productId);
	List<ImageDto> saveImages(List<MultipartFile> files,Long imageId);
	void updateImage(MultipartFile file,Long imageId);
}
