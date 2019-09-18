package src.micro.apigatway.main.interfaceData;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface HomeInterface {

	public void getFileUpload(MultipartFile file) throws IOException;
}
