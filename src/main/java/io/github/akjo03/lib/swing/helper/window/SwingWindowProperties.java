package io.github.akjo03.lib.swing.helper.window;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.akjo03.lib.path.FilePaths;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@ToString
public class SwingWindowProperties {
	private static final String FILE_NAME = "window.json";
	private static final ObjectMapper MAPPER = new ObjectMapper()
			.registerModule(new Jdk8Module())
			.registerModule(new JavaTimeModule());

	@JsonSerialize
	@JsonDeserialize
	private int windowWidth;

	@JsonSerialize
	@JsonDeserialize
	private int windowHeight;

	@JsonSerialize
	@JsonDeserialize
	private int windowPosX;

	@JsonSerialize
	@JsonDeserialize
	private int windowPosY;

	@JsonSerialize
	@JsonDeserialize
	private int windowState;

	private SwingWindowProperties(
			@JsonProperty("window_width") int windowWidth,
			@JsonProperty("window_height") int windowHeight,
			@JsonProperty("window_pos_x") int windowPosX,
			@JsonProperty("window_pos_y") int windowPosY,
			@JsonProperty("window_state") int windowState
	) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.windowPosX = windowPosX;
		this.windowPosY = windowPosY;
		this.windowState = windowState;
	}

	public static @NotNull SwingWindowProperties initialize(
			@NotNull String appName,
			int windowWidth,
			int windowHeight,
			int windowPosX,
			int windowPosY,
			int windowState
	) throws Exception {
		Path path = FilePaths.createAppDirectory(appName);
		SwingWindowProperties properties = new SwingWindowProperties(
				windowWidth,
				windowHeight,
				windowPosX,
				windowPosY,
				windowState
		);
		MAPPER.writeValue(path.resolve(FILE_NAME).toFile(), properties);
		return properties;
	}

	public static boolean exists(String appName) {
		return Files.exists(FilePaths.getAppDirectory(appName).resolve(FILE_NAME));
	}

	public static SwingWindowProperties load(@NotNull Frame frame, @NotNull String appName) throws Exception {
		Path path = FilePaths.getAppDirectory(appName);
		try {
			return MAPPER.readValue(path.resolve(FILE_NAME).toFile(), SwingWindowProperties.class);
		} catch (Exception e) {
			return initialize(
					appName,
					frame.getWidth(), frame.getHeight(),
					frame.getX(), frame.getY(),
					frame.getExtendedState()
			);
		}
	}

	public static void saveSize(@NotNull String appName, int windowWidth, int windowHeight) throws Exception {
		Path path = FilePaths.getAppDirectory(appName);
		SwingWindowProperties properties = MAPPER.readValue(path.resolve(FILE_NAME).toFile(), SwingWindowProperties.class);
		properties.setWindowWidth(windowWidth);
		properties.setWindowHeight(windowHeight);
		MAPPER.writeValue(path.resolve(FILE_NAME).toFile(), properties);
	}

	public static void savePosition(@NotNull String appName, int windowPosX, int windowPosY) throws Exception {
		Path path = FilePaths.getAppDirectory(appName);
		SwingWindowProperties properties = MAPPER.readValue(path.resolve(FILE_NAME).toFile(), SwingWindowProperties.class);
		properties.setWindowPosX(windowPosX);
		properties.setWindowPosY(windowPosY);
		MAPPER.writeValue(path.resolve(FILE_NAME).toFile(), properties);
	}

	public static void saveState(@NotNull String appName, int windowState) throws Exception {
		Path path = FilePaths.getAppDirectory(appName);
		SwingWindowProperties properties = MAPPER.readValue(path.resolve(FILE_NAME).toFile(), SwingWindowProperties.class);
		properties.setWindowState(windowState);
		MAPPER.writeValue(path.resolve(FILE_NAME).toFile(), properties);
	}
}