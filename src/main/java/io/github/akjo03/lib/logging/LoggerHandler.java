package io.github.akjo03.lib.logging;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
@SuppressWarnings("unused")
public class LoggerHandler {
	private static final Logger LOGGER = LoggerManager.getLogger(LoggerHandler.class);

	public void initialize(ApplicationContext ctx) {
		Arrays.stream(ctx.getBeanDefinitionNames()).forEach(beanName -> {
			Object obj = ctx.getBean(beanName);
			if (obj.getClass().isAnnotationPresent(EnableLogger.class)) {
				Arrays.stream(obj.getClass().getDeclaredFields()).forEach(field -> {
					if (field.getType().equals(Logger.class)) {
						field.setAccessible(true);

						try {
							field.set(obj, LoggerManager.getLogger(obj.getClass()));
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
}