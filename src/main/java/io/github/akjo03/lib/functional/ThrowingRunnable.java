package io.github.akjo03.lib.functional;

@FunctionalInterface
@SuppressWarnings("unused")
public interface ThrowingRunnable<T extends Throwable> {
	void run() throws T;
}