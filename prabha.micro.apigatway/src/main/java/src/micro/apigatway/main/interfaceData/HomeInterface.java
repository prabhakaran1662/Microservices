package src.micro.apigatway.main.interfaceData;

import org.springframework.web.multipart.MultipartFile;

public interface HomeInterface {

	public String getFileUpload(MultipartFile file);
}
