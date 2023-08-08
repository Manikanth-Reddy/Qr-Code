package com.mani.QrCode.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mani.QrCode.entity.Image;
import com.mani.QrCode.entity.QrCode;
import com.mani.QrCode.service.ImageService;

@Controller
public class QrController {
	@Autowired
	private ImageService service;

	@RequestMapping("/")
	public ModelAndView display() {
		ModelAndView view = new ModelAndView("home");
		view.addObject("qrCode", new QrCode());
		return view;
	}

	@PostMapping("/generate")
	public ResponseEntity<?> generate(@ModelAttribute("qrCode") QrCode qrcode) throws WriterException, IOException {
		var qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(qrcode.getContent(), BarcodeFormat.QR_CODE, 400, 400);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
		Image saveImage = service.saveImage(outputStream.toByteArray(), qrcode.getName());
		byte[] bs = service.getImage(saveImage.getId());
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(bs);
	}	
}
