package io.github.akjo03.lib.result;

@FunctionalInterface
public interface ThrowingSupplier<S> {
	S get() throws Throwable;
}