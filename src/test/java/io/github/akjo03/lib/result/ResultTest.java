package io.github.akjo03.lib.result;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {
	@Test
	public void testSuccess() {
		String data = "Hello, World!";
		Result<String> result = Result.success(data);

		assertTrue(result.isSuccess());
		assertFalse(result.isError());
		assertFalse(result.isEmpty());
		assertEquals(data, result.get());
		assertThrows(NoSuchElementException.class, result::getError);
	}

	@Test
	public void testFail() {
		Throwable throwable = new Exception("Test");
		Result<String> result = Result.fail(throwable);

		assertFalse(result.isSuccess());
		assertTrue(result.isError());
		assertFalse(result.isEmpty());
		assertThrows(NoSuchElementException.class, result::get);
		assertEquals(throwable, result.getError());
	}

	@Test
	public void testEmpty() {
		Result<?> result = Result.empty();

		assertTrue(result.isSuccess());
		assertFalse(result.isError());
		assertTrue(result.isEmpty());
		assertNull(result.get());
		assertThrows(NoSuchElementException.class, result::getError);
	}

	@Test
	public void testFrom_success() {
		String data = "Hello, World!";
		Result<String> result = Result.from(() -> data);

		assertTrue(result.isSuccess());
		assertFalse(result.isError());
		assertFalse(result.isEmpty());
		assertEquals(data, result.get());
		assertThrows(NoSuchElementException.class, result::getError);
	}

	@Test
	public void testFrom_fail() {
		Throwable throwable = new Exception("Test");
		Result<String> result = Result.from(() -> {
			throw throwable;
		});

		assertFalse(result.isSuccess());
		assertTrue(result.isError());
		assertFalse(result.isEmpty());
		assertThrows(NoSuchElementException.class, result::get);
		assertEquals(throwable, result.getError());
	}

	@Test
	public void testGetOrElse() {
		String data = "Hello, World!";

		Result<String> result = Result.success(data);
		assertEquals(data, result.getOrElse("Test"));

		result = Result.fail(new Exception("Test"));
		assertEquals("Test", result.getOrElse("Test"));
	}

	@Test
	public void testGetOrElseFunction() {
		String data = "Hello, World!";

		Result<String> result = Result.success(data);
		assertEquals(data, result.getOrApply(e -> "Test"));

		Exception exception = new Exception("Test");
		result = Result.fail(exception);
		assertEquals("Failed: Test", result.getOrApply(e -> "Failed: " + e.getMessage()));
	}

	@Test
	public void testGetOrNull() {
		String data = "Hello, World!";

		Result<String> result = Result.success(data);
		assertEquals(data, result.getOrNull());

		result = Result.fail(new Exception("Test"));
		assertNull(result.getOrNull());
	}

	@Test
	public void testGetOrThrow() throws Throwable {
		String data = "Hello, World!";

		Result<String> result = Result.success(data);
		assertEquals(data, result.getOrThrow());

		Throwable throwable = new Exception("Test");
		result = Result.fail(throwable);
		assertEquals(throwable, result.getError());
		assertThrows(Exception.class, result::getOrThrow);
	}

	@Test
	public void testMap() {
		String data = "Hello, World!";
		Result<String> result = Result.success(data);

		Function<String, Integer> function = String::length;
		Result<Integer> lengthResult = result.map(function);

		assertTrue(lengthResult.isSuccess());
		assertEquals(data.length(), lengthResult.get());

		result = Result.fail(new Exception("Test"));
		lengthResult = result.map(function);

		assertTrue(lengthResult.isError());
		assertEquals("Test", lengthResult.getError().getMessage());
	}

	@Test
	public void testMapError() {
		Throwable throwable = new Exception("Test");
		Result<String> result = Result.fail(throwable);

		Function<Throwable, Throwable> function = RuntimeException::new;
		Result<String> mappedResult = result.mapError(function);

		assertTrue(mappedResult.isError());
		assertTrue(mappedResult.getError() instanceof RuntimeException);
		assertEquals(throwable, mappedResult.getError().getCause());

		result = Result.success("Hello, World!");
		mappedResult = result.mapError(function);

		assertTrue(mappedResult.isSuccess());
		assertEquals("Hello, World!", mappedResult.get());
	}

	@Test
	public void testIfError() {
		Throwable throwable = new Exception("Test");
		Result<String> result = Result.fail(throwable);

		final Throwable[] capturedThrowable = new Exception[2];
		result.ifError(e -> capturedThrowable[0] = e);

		assertEquals(throwable, capturedThrowable[0]);

		result = Result.success("Hello, World!");
		result.ifError(e -> capturedThrowable[1] = e);

		assertNull(capturedThrowable[1]);
	}

	@Test
	public void testIfErrorTyped() {
		Throwable throwable = new Exception("Test");
		Result<String> result = Result.fail(throwable);

		final Throwable[] capturedThrowable = new Exception[3];
		result.ifError(Exception.class, e -> capturedThrowable[0] = e);

		assertEquals(throwable, capturedThrowable[0]);

		result = Result.success("Hello, World!");
		result.ifError(Exception.class, e -> capturedThrowable[1] = e);

		assertNull(capturedThrowable[1]);

		result.ifError(NullPointerException.class, e -> capturedThrowable[2] = e);

		assertNull(capturedThrowable[2]);
	}

	@Test
	public void testIfSuccess() {
		String data = "Hello, World!";
		Result<String> result = Result.success(data);

		final String[] capturedData = new String[2];
		result.ifSuccess(s -> capturedData[0] = s);

		assertEquals(data, capturedData[0]);

		result = Result.fail(new Exception("Test"));
		result.ifSuccess(s -> capturedData[1] = s);

		assertNull(capturedData[1]);
	}

	@Test
	public void testIfPresent() {
		String data = "Hello, World!";
		Result<String> result = Result.success(data);

		final String[] capturedData = new String[2];
		final Throwable[] capturedThrowable = new Exception[2];

		result.ifPresent(
			s -> capturedData[0] = s,
			e -> capturedThrowable[0] = e
		);

		assertEquals(data, capturedData[0]);
		assertNull(capturedThrowable[0]);

		result = Result.fail(new Exception("Test"));
		result.ifPresent(
			s -> capturedData[1] = s,
			e -> capturedThrowable[1] = e
		);

		assertNull(capturedData[1]);
		assertEquals("Test", capturedThrowable[1].getMessage());
	}

	@Test
	public void testWrapError() {
		Throwable throwable = new Exception("Test");
		Result<String> result = Result.fail(throwable);

		Result<String> wrappedResult = result.wrapError(RuntimeException::new, "Wrapped");

		assertTrue(wrappedResult.isError());
		assertTrue(wrappedResult.getError() instanceof RuntimeException);
		assertEquals("Wrapped", wrappedResult.getError().getMessage());
		assertEquals(throwable, wrappedResult.getError().getCause());

		result = Result.success("Hello, World!");
		wrappedResult = result.wrapError(RuntimeException::new, "Wrapped");

		assertTrue(wrappedResult.isSuccess());
		assertEquals("Hello, World!", wrappedResult.get());
	}

	@Test
	public void testWrapErrorJustMap() {
		Throwable throwable = new Exception("Test");
		Result<String> result = Result.fail(throwable);

		Function<Throwable, Throwable> function = RuntimeException::new;
		Result<String> mappedResult = result.wrapError(function);

		assertTrue(mappedResult.isError());
		assertTrue(mappedResult.getError() instanceof RuntimeException);
		assertEquals(throwable, mappedResult.getError().getCause());

		result = Result.success("Hello, World!");
		mappedResult = result.wrapError(function);

		assertTrue(mappedResult.isSuccess());
		assertEquals("Hello, World!", mappedResult.get());
	}

	@Test
	public void testAsOptional() {
		String data = "Hello, World!";
		Result<String> result = Result.success(data);

		Optional<String> optional = result.asOptional();

		assertTrue(optional.isPresent());
		assertEquals(data, optional.get());
	}
}