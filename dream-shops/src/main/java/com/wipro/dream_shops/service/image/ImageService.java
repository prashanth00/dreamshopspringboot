package com.wipro.dream_shops.service.image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.*;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.dream_shops.dto.ImageDto;
import com.wipro.dream_shops.exceptions.ResourceNotFoundException;
import com.wipro.dream_shops.model.Image;
import com.wipro.dream_shops.repository.ImageRepository;
import com.wipro.dream_shops.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

	private final ImageRepository imageRepository;
	private final IProductService productService; 
	@Override
	public Image getImageById(Long id) {
		// TODO Auto-generated method stub
		return imageRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No image found with id:"+id));
	}

	@Override
	public void deleteImageById(Long id) {
		// TODO Auto-generated method stub
		imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, ()->
			 new ResourceNotFoundException("No image found with id :"+id)
		);
		
	}

	@Override
	public Image getImageByName(MultipartFile file, Long productId) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public List<ImageDto> saveImages(List<MultipartFile> files,Long productId) {
		Product product = productService.getProductById(productId);
		List<ImageDto> savedImageDto=new ArrayList<>();
		for(MultipartFile file:files) {
			try {
				Image image=new Image();
				image.setFileName(file.getOriginalFilename());
				image.setFileType(file.getContentType());
				image.setImage(new SerialBlob(file.getBytes()));
				image.setProduct(product);
				
				String buildDownloadUrl="/api/v1/images/image/download/";
				String downloadUrl = "/api/v1/images/image/download/"+image.getId();
				image.setDownloadUrl(downloadUrl);
				Image savedImage=imageRepository.save(image);
				
				savedImage.setDownloadUrl(buildDownloadUrl"/api/v1/images/image/download/"+savedImage.getId());
				imageRepository.save(savedImage);
				
				ImageDto imageDto=new ImageDto();
				ImageDto.setImageId(savedImage.getId());
				imageDto.setImageName(savedImage.getFileName());
				imageDto.setDownloadUrl(savedImage.getDownloadUrl());
				savedImageDto.add(imageDto);
			}catch(IOException e | SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return savedImageDto;
	}

	@Override
	public void updateImage(MultipartFile file, Long imageId) {
		// TODO Auto-generated method stub
		try {
		Image image=getImageById(imageId);
		image.setFileName(file.getOriginalFilename());
		image.setFileType(file.getContentType());
		image.setImage(new SerialBlob(file.getBytes()));
		imageRepository.save(image);
	}catch(IOException | SQLException e) {
		throw new RuntimeException(e.getMessage());
	}
	}
	
}
