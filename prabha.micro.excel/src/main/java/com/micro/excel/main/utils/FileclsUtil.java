package com.micro.excel.main.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

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
					System.out.println("absolutePath "+absolutePath);
				}
			} else {
				if (!filefullDir.exists()) {
					filefullDir.mkdir();
					System.out.println("fullPath "+fullPath);
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
	  
	        catch (Exception e) { 
	            System.out.println("Exception: " + e); 
	        } 
	}


}
