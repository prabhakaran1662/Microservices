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
	public void getFileUpload(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		LocalDateTime now = LocalDateTime.now();
		CustomerData cusData = new CustomerData();		
		cusData.setDateTime(dtf.format(now));
		cusData.setFileName(file.getOriginalFilename());
		cusData.setFileType(file.getContentType());
		cusData.setBytes(file.getBytes());

		if(file.getContentType().equalsIgnoreCase("application/pdf")) {
			System.out.println("cusData ==>> "+cusData);
			producer.pdfProduce(cusData);
		}else if(file.getContentType().equalsIgnoreCase("application/vnd.ms-excel") || file.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			producer.excelProduce(cusData);
		}
	}

}
