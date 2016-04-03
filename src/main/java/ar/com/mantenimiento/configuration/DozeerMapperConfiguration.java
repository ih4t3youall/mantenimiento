package ar.com.mantenimiento.configuration;

import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozeerMapperConfiguration {

	
	


	  @Bean(name = "org.dozer.Mapper")
	  public DozerBeanMapper dozerBean() {
		  System.out.println("Instancia de dozzer mapper");
//	    List<String> mappingFiles = Arrays.asList(
//	      "dozer-global-configuration.xml", 
//	      "dozer-bean-mappings.xml",
//	      "more-dozer-bean-mappings.xml"
//	    );
	    List<String> mappingFiles = Arrays.asList(
//	  	      "dozer-global-configuration.xml", 
//	  	      "dozer-bean-mappings.xml",
	  	      "formDTOtoForm.xml"
//	  	      "legacyDTO.xml"
	  	    );
	  	    
	    DozerBeanMapper dozerBean = new DozerBeanMapper();
	    dozerBean.setMappingFiles(mappingFiles);
	    return dozerBean;
	  }

}
