package src.micro.apigatway.main.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import src.micro.apigatway.main.been.CustomerData;

@Component
public class Producer {
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${prabha.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${pdf.rabbitmq.routingkey}")
	private String pdfRoutingkey;
	
	@Value("${excel.rabbitmq.routingkey}")
	private String ExcelRoutingkey;
	
	

	public void pdfProduce(CustomerData cusData) {
		amqpTemplate.convertAndSend(exchange, pdfRoutingkey, cusData);
		System.out.println("Send Pdf data ==>> "+cusData);
	}
	
	public void excelProduce(CustomerData cusData) {
		amqpTemplate.convertAndSend(exchange, ExcelRoutingkey, cusData);
		System.out.println("Send Excel data ==>> "+cusData);
	}
	
}
