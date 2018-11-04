package be.vdab.ethias.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class CarSoapServiceEndpointConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}

	@Bean(name = "cars")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema carsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CarsPort");
		wsdl11Definition.setLocationUri("/ws/");
		wsdl11Definition.setTargetNamespace("http://vdab.be/ethias/wsdl");
		wsdl11Definition.setSchema(carsSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("cars.xsd"));
	}
}
