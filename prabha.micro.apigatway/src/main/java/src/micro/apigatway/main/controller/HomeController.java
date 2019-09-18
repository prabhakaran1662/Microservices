package src.micro.apigatway.main.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import src.micro.apigatway.main.interfaceData.HomeInterface;

@RestController
public class HomeController {
	
	@Autowired
	private HomeInterface homeInterface;
	
	@RequestMapping(value="/fileupload/", method=RequestMethod.POST)
	public ResponseEntity<Void> getFileUpload(@RequestParam MultipartFile file) {
		System.out.println("--->>>>"+file.getOriginalFilename());
		try {
			homeInterface.getFileUpload(file);	
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
}
