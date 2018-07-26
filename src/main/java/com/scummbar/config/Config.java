package com.scummbar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@ComponentScan("com.scummbar")
@EnableWebMvc
public class Config {

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public UrlBasedViewResolver viewResolver(){
	UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
	viewResolver.setViewClass(TilesView.class);
	return viewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer(){
	TilesConfigurer tilesConfigurer = new TilesConfigurer();
	tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/tiles/tiles.xml"});
	return tilesConfigurer;
	}
}
