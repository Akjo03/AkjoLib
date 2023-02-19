package io.github.akjo03.lib.config;

import io.github.akjo03.lib.json.JsonPrettyPrinter;
import io.github.akjo03.lib.logging.LoggerHandler;
import io.github.akjo03.lib.path.ProjectDirectory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "akjo.lib")
@ConditionalOnClass({
		LoggerHandler.class,
		ProjectDirectory.class,
		JsonPrettyPrinter.class
})
@ComponentScan(basePackages = "io.github.akjo03.lib")
@SuppressWarnings("unused")
public class AkjoLibSpringAutoConfiguration {
	@Bean
	@ConditionalOnMissingBean
	public LoggerHandler loggerHandler() {
		return new LoggerHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public ProjectDirectory projectDirectory() {
		return new ProjectDirectory();
	}

	@Bean
	@ConditionalOnMissingBean
	public JsonPrettyPrinter jsonPrettyPrinter() {
		return new JsonPrettyPrinter();
	}
}