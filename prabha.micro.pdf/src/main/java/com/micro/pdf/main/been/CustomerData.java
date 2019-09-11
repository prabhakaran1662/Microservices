package com.micro.pdf.main.been;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;


public class CustomerData implements Serializable{
	
	private String fileName;
	private String fileType;
	private String dateTime;
	private MultipartFile uploadFile;
	private byte[] bytes;

	
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	@Override
	public String toString() {
		return "CustomerData [fileName=" + fileName + ", fileType=" + fileType + ", dateTime=" + dateTime
				+ ", uploadFile=" + uploadFile + ", bytes=" + Arrays.toString(bytes) + "]";
	}
	
	
}
