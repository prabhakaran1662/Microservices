package com.micro.pdf.main.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.micro.pdf.main.been.CustomerData;
import com.micro.pdf.main.services.PdfServices;

@Component
public class Consumer {

	@Autowired
	PdfServices pdfServices;
	
	@RabbitListener(queues = "${pdf.rabbitmq.queue}")
	public void recievedMessage(CustomerData cusData) {
		try {
			System.out.println("Received Data ==>> "+cusData);
			pdfServices.writeByte(cusData.getBytes(),cusData.getDateTime(),cusData.getFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
