package com.micro.excel.main.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micro.excel.main.been.CustomerData;
import com.micro.excel.main.services.ExcelServices;

@Component
public class Consumer {
	
	@Autowired
	ExcelServices excelServices;

	@RabbitListener(queues = "${excel.rabbitmq.queue}")
	public void recievedMessage(CustomerData cusData) {
		try {
			System.out.println("Received Data ==>> "+cusData);
			excelServices.writeByte(cusData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
