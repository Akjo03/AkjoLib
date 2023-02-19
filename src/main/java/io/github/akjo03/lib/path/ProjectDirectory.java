package io.github.akjo03.lib.path;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@SuppressWarnings("unused")
public class ProjectDirectory {
	public @NotNull Path getProjectRootDirectory() {
		String envRootDir = System.getProperty("user.dir");
		Path rootDir = Paths.get(".").normalize().toAbsolutePath();
		if (rootDir.startsWith(envRootDir)) {
			return rootDir;
		} else {
			return Path.of(envRootDir);
		}
	}
}