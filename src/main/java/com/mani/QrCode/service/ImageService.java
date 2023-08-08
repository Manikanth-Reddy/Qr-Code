package com.mani.QrCode.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mani.QrCode.entity.Image;
import com.mani.QrCode.repository.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository repository;
	
	public Image saveImage(byte[] bs,String name) {
		Image image=new Image();
		image.setImageData(bs);
		image.setName(name);
		Image save = repository.save(image);
		return save;
	}
	public byte[] getImage(Long id){
		Optional<Image> optional = repository.findById(id);
		return optional.get().getImageData();
	}
}
