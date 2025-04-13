package edu.uniuv.grupo2.tourgemeas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Configuration
public class JacksonConfig {
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
			@Override
			public boolean hasIgnoreMarker(AnnotatedMember m) {
				if (
					m.hasAnnotation(OneToMany.class) ||
					m.hasAnnotation(OneToOne.class) ||
					m.hasAnnotation(ManyToOne.class) ||
					m.hasAnnotation(ManyToMany.class)
				) {
					return true;
				}
				return super.hasIgnoreMarker(m);
			}
		});
		return mapper;
	}
}
