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
		Exception exception = new Exception("Test");
		Result<String> result = Result.fail(exception);

		assertFalse(result.isSuccess());
		assertTrue(result.isError());
		assertFalse(result.isEmpty());
		assertThrows(NoSuchElementException.class, result::get);
		assertEquals(exception, result.getError());
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
		Exception exception = new Exception("Test");
		Result<String> result = Result.from(() -> {
			throw exception;
		});

		assertFalse(result.isSuccess());
		assertTrue(result.isError());
		assertFalse(result.isEmpty());
		assertThrows(NoSuchElementException.class, result::get);
		assertEquals(exception, result.getError());
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
		assertEquals(data, result.getOrElse(e -> "Test"));

		Exception exception = new Exception("Test");
		result = Result.fail(exception);
		assertEquals("Failed: Test", result.getOrElse(e -> "Failed: " + e.getMessage()));
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
	public void testGetOrThrow() throws Exception {
		String data = "Hello, World!";

		Result<String> result = Result.success(data);
		assertEquals(data, result.getOrThrow());

		Exception exception = new Exception("Test");
		result = Result.fail(exception);
		assertEquals(exception, result.getError());
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
		Exception exception = new Exception("Test");
		Result<String> result = Result.fail(exception);

		Function<Exception, Exception> function = RuntimeException::new;
		Result<String> mappedResult = result.mapError(function);

		assertTrue(mappedResult.isError());
		assertTrue(mappedResult.getError() instanceof RuntimeException);
		assertEquals(exception, mappedResult.getError().getCause());

		result = Result.success("Hello, World!");
		mappedResult = result.mapError(function);

		assertTrue(mappedResult.isSuccess());
		assertEquals("Hello, World!", mappedResult.get());
	}

	@Test
	public void testIfError() {
		Exception exception = new Exception("Test");
		Result<String> result = Result.fail(exception);

		final Exception[] capturedException = new Exception[2];
		result.ifError(e -> capturedException[0] = e);

		assertEquals(exception, capturedException[0]);

		result = Result.success("Hello, World!");
		result.ifError(e -> capturedException[1] = e);

		assertNull(capturedException[1]);
	}

	@Test
	public void testIfErrorTyped() {
		Exception exception = new Exception("Test");
		Result<String> result = Result.fail(exception);

		final Exception[] capturedException = new Exception[3];
		result.ifError(Exception.class, e -> capturedException[0] = e);

		assertEquals(exception, capturedException[0]);

		result = Result.success("Hello, World!");
		result.ifError(Exception.class, e -> capturedException[1] = e);

		assertNull(capturedException[1]);

		result.ifError(NullPointerException.class, e -> capturedException[2] = e);

		assertNull(capturedException[2]);
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
		final Exception[] capturedException = new Exception[2];

		result.ifPresent(
			s -> capturedData[0] = s,
			e -> capturedException[0] = e
		);

		assertEquals(data, capturedData[0]);
		assertNull(capturedException[0]);

		result = Result.fail(new Exception("Test"));
		result.ifPresent(
			s -> capturedData[1] = s,
			e -> capturedException[1] = e
		);

		assertNull(capturedData[1]);
		assertEquals("Test", capturedException[1].getMessage());
	}

	@Test
	public void testWrapError() {
		Exception exception = new Exception("Test");
		Result<String> result = Result.fail(exception);

		Result<String> wrappedResult = result.wrapError(RuntimeException::new, "Wrapped");

		assertTrue(wrappedResult.isError());
		assertTrue(wrappedResult.getError() instanceof RuntimeException);
		assertEquals("Wrapped", wrappedResult.getError().getMessage());
		assertEquals(exception, wrappedResult.getError().getCause());

		result = Result.success("Hello, World!");
		wrappedResult = result.wrapError(RuntimeException::new, "Wrapped");

		assertTrue(wrappedResult.isSuccess());
		assertEquals("Hello, World!", wrappedResult.get());
	}

	@Test
	public void testWrapErrorJustMap() {
		Exception exception = new Exception("Test");
		Result<String> result = Result.fail(exception);

		Function<Exception, Exception> function = RuntimeException::new;
		Result<String> mappedResult = result.wrapError(function);

		assertTrue(mappedResult.isError());
		assertTrue(mappedResult.getError() instanceof RuntimeException);
		assertEquals(exception, mappedResult.getError().getCause());

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