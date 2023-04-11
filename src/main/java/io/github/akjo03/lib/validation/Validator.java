package io.github.akjo03.lib.validation;

import io.github.akjo03.lib.result.Result;

@FunctionalInterface
@SuppressWarnings("unused")
public interface Validator<T> {
	Result<T> validate(T t);
}