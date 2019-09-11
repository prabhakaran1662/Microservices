package src.micro.apigatway.main.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import src.micro.apigatway.main.been.CustomerData;
import src.micro.apigatway.main.config.Producer;
import src.micro.apigatway.main.interfaceData.HomeInterface;

@Service
public class HomeServices implements HomeInterface {

	@Autowired
	public Producer producer;
	
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Override	
	public String getFileUpload(MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println("file type ==>> "+file.getContentType());
		LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now).toString());
		CustomerData cusData = new CustomerData();		
		cusData.setDateTime(dtf.format(now));
		cusData.setFileName(file.getOriginalFilename());
		cusData.setFileType(file.getContentType());
	//	cusData.setUploadFile(file);
		try {
			cusData.setBytes(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(file.getContentType().equalsIgnoreCase("application/pdf")) {
			System.out.println("cusData ==>> "+cusData);
			producer.pdfProduce(cusData);
		}else if(file.getContentType().equalsIgnoreCase("application/vnd.ms-excel") || file.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			producer.excelProduce(cusData);
		}
		return null;
	}

}
