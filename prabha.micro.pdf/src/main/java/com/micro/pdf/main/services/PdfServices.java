package com.micro.pdf.main.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micro.pdf.main.utils.FileclsUtil;

@Component
public class PdfServices {
	
	@Autowired
	FileclsUtil fileclsUtil;
	
	public void writeByte(byte[] bytes,String datetime,String fileName) 
    { 		
		String filePath = fileclsUtil.createFolder(datetime)+"/"+fileName;
		fileclsUtil.fileWriter( bytes,filePath );
		pdfExtractService(filePath);
    } 
	
	public void pdfExtractService(String filePath) {
		try {
		    String text = fileclsUtil.getPdfText(new File(filePath));
		    System.out.println("Text in PDF: " + text);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
