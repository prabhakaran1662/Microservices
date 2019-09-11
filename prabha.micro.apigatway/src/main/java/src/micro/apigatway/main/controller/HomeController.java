package src.micro.apigatway.main.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String getFileUpload(@RequestParam MultipartFile file) {
		System.out.println("--->>>>"+file.getOriginalFilename());
		homeInterface.getFileUpload(file);	
		 return null;
	}
}
