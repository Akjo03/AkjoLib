package io.github.akjo03.lib.path;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("unused")
public final class FilePaths {
	public static @NotNull Path getAppDirectory(String appName) {
		String os = System.getProperty("os.name").toLowerCase();
		Path basePath;
		if (os.contains("win")) {
			basePath = Path.of(System.getenv("APPDATA"));
		} else if (os.contains("mac")) {
			basePath = Path.of(System.getProperty("user.home"), "Library", "Application Support");
		} else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
			basePath = Path.of(System.getProperty("user.home"), ".config");
		} else {
			basePath = Path.of(System.getProperty("user.dir"));
		}

		return basePath.resolve(appName.replace(' ', '_').toLowerCase());
	}

	public static @NotNull Path createAppDirectory(String appName) throws IOException {
		Path path = getAppDirectory(appName.replace(' ', '_').toLowerCase());
		if (!Files.exists(path)) Files.createDirectories(path);
		return path;
	}
}