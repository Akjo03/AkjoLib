package io.github.akjo03.lib.builder;

@FunctionalInterface
public interface Builder<T extends Buildable> {
	T build();
}