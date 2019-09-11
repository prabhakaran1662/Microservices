package src.micro.apigatway.main.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("${excel.rabbitmq.queue}")
	String ExcelQueueName;
	
	@Value("${pdf.rabbitmq.queue}")
	String pdfQueueName;
	
	@Value("${prabha.rabbitmq.exchange}")
	String exchange;
	
	@Value("${pdf.rabbitmq.routingkey}")
	private String pdfRoutingkey;
	
	@Value("${excel.rabbitmq.routingkey}")
	private String ExcelRoutingkey;
		
    @Bean
    Queue excelQueue() {
        return new Queue(ExcelQueueName, true);
    }
    
    @Bean
    Queue pdfQueue() {
        return new Queue(pdfQueueName, true);
    }
    
    @Bean
    DirectExchange exchange() {
    	return new DirectExchange(exchange);
    }

    @Bean
    Binding excelBinding(Queue excelQueue, DirectExchange exchange) {
        return BindingBuilder.bind(excelQueue).to(exchange).with(ExcelRoutingkey);
    }
    
    @Bean
    Binding pdfBinding(Queue pdfQueue, DirectExchange exchange) {
        return BindingBuilder.bind(pdfQueue).to(exchange).with(pdfRoutingkey);
    }
    
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
