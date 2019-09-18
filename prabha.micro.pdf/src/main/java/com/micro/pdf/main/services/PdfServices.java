package com.micro.pdf.main.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.pdf.main.been.PerficientData;
import com.micro.pdf.main.repository.FileInventoryRepository;
import com.micro.pdf.main.utils.FileclsUtil;

@Service
@Transactional
public class PdfServices {

	@Autowired
	FileclsUtil fileclsUtil;

	@Autowired
	FileInventoryRepository repository;

	public void writeByte(byte[] bytes, String datetime, String fileName) {
		try {
			String filePath = fileclsUtil.createFolder(datetime) + "/" + fileName;
			fileclsUtil.fileWriter(bytes, filePath);
			pdfExtractService(filePath, datetime);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void pdfExtractService(String filePath, String datetime) {
		try {
			PerficientData perficientData = new PerficientData();

			String text = fileclsUtil.getPdfText(new File(filePath));
			System.out.println("Text in PDF: " + text);
			perficientData.setData(text);
			perficientData.setType("pdf");
			perficientData.setLastupdate(datetime);
			repository.save(perficientData);
			List<PerficientData> dataList = repository.findLastElement(PageRequest.of(0, 1) );
			System.out.println("===>>> "+dataList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
