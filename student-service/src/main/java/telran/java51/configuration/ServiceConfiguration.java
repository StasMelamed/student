package telran.java51.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
  
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration()
		
		.setFieldAccessLevel(AccessLevel.PRIVATE)
		.setFieldMatchingEnabled(true);
		
		return modelMapper;
	} 
}
