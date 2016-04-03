package ar.com.mantenimiento.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = { "ar.com.mantenimiento.*" })
@PropertySource("classpath:filesystem.properties")
public class AppConfig {

	@Autowired
	Environment env;

	@Bean
	public String pathIagen() {

		return env.getProperty("path.images");
	}

	@Bean
	public String pathPdf() {

		return env.getProperty("path.pdf");

	}
	
	@Bean
	public String pathLogoEmpresa(){
		
		return env.getProperty("path.logo");
		
		
	}

}