package com.nerus.springboot.validardocs.app.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class GenerarCodigoService {
	
	public String generarCodigo(String content, int imageSize) throws WriterException, IOException {
		
		//*** Generar codigo QR
		Writer writer = new QRCodeWriter();
		BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, imageSize, imageSize);
		//MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.BLACK, 10);
	    //MatrixToImageWriterEx.writeToFile(matrix, fileType, savePath, logoPath, logoConfig);
		
		//*** Convertir QR to image
		BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		ImageIO.write(qrImage, "png", oStream);
		return Base64.getEncoder().encodeToString(oStream.toByteArray());
		
	}

}
