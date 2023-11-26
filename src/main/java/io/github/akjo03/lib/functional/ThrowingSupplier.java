package io.github.akjo03.lib.functional;

@FunctionalInterface
public interface ThrowingSupplier<S, T extends Throwable> {
	S get() throws T;
}