package com.micro.pdf.main.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

@Component
public class FileclsUtil {

	public String createFolder(String dateTime) {
		
		try {
			String[] folders = dateTime.split(" ");
			File file = new File("");
			String absolutePath = file.getAbsolutePath()+"/"+folders[0];
			String fullPath = absolutePath+"/"+folders[1].replaceAll(":", "-");
			File filefullDir = new File(fullPath);
			 
	        File fileDir = new File(absolutePath);
			if (!fileDir.exists()) {
				if (fileDir.mkdir()) {
					filefullDir.mkdir();
				}
			} else {
				if (!filefullDir.exists()) {
					filefullDir.mkdir();
				} 
			}

	        return fullPath;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public void fileWriter(byte[] bytes, String path) {
		 try {   
	    		OutputStream  os = new FileOutputStream(path);   
	            os.write(bytes); 
	            System.out.println("Successfully byte inserted");   
	            os.close(); 
	        } 
	  
	        catch (IOException e) { 
	            System.out.println("Exception: " + e); 
	            e.printStackTrace();
	        } 
	}
	
	public String getPdfText(File pdfFile) throws IOException {
		try {
	    PDDocument doc = PDDocument.load(pdfFile);
	    return new PDFTextStripper().getText(doc);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
