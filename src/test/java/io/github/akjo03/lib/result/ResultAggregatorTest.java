package io.github.akjo03.lib.result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResultAggregatorTest {
	private ResultAggregator resultAggregator;
	private Result<Object> successResult1;
	private Result<Object> successResult2;
	private Result<Object> errorResult;

	@BeforeEach
	public void setUp() {
		resultAggregator = new ResultAggregator();
		successResult1 = Result.success("Success 1");
		successResult2 = Result.success("Success 2");
		errorResult = Result.fail(new Exception("Error"));
	}

	@Test
	public void testAggregateFirst_success() {
		resultAggregator.add(successResult1);
		resultAggregator.add(successResult2);
		Result<Object> result = resultAggregator.aggregateFirst();
		assertTrue(result.isSuccess());
		assertEquals("Success 1", result.get());
	}

	@Test
	public void testAggregateFirst_error() {
		resultAggregator.add(successResult1);
		resultAggregator.add(errorResult);
		Result<Object> result = resultAggregator.aggregateFirst();
		assertTrue(result.isError());
	}

	@Test
	public void testAggregateFirst_empty() {
		Result<Object> result = resultAggregator.aggregateFirst();
		assertTrue(result.isSuccess());
		assertNull(result.get());
	}

	@Test
	public void testAggregateLast_success() {
		resultAggregator.add(successResult1);
		resultAggregator.add(successResult2);
		Result<Object> result = resultAggregator.aggregateLast();
		assertTrue(result.isSuccess());
		assertEquals("Success 2", result.get());
	}

	@Test
	public void testAggregateLast_error() {
		resultAggregator.add(errorResult);
		resultAggregator.add(successResult1);
		Result<Object> result = resultAggregator.aggregateLast();
		assertTrue(result.isError());
	}

	@Test
	public void testAggregateLast_empty() {
		Result<Object> result = resultAggregator.aggregateLast();
		assertTrue(result.isSuccess());
		assertNull(result.get());
	}

	@Test
	public void testAggregateFor_success() {
		resultAggregator.add(successResult1);
		resultAggregator.add(successResult2);
		Result<Object> result = resultAggregator.aggregateFor(Result::isSuccess);
		assertTrue(result.isSuccess());
		assertEquals("Success 1", result.get());
	}

	@Test
	public void testAggregateFor_error() {
		resultAggregator.add(successResult1);
		resultAggregator.add(errorResult);
		Result<Object> result = resultAggregator.aggregateFor(Result::isSuccess);
		assertTrue(result.isError());
	}

	@Test
	public void testAggregateFor_empty() {
		Result<Object> result = resultAggregator.aggregateFor(Result::isSuccess);
		assertTrue(result.isSuccess());
		assertNull(result.get());
	}

	@Test
	public void testAggregateAll_success() {
		resultAggregator.add(successResult1);
		resultAggregator.add(successResult2);
		Result<List<Object>> result = resultAggregator.aggregateAll();
		assertTrue(result.isSuccess());
		assertEquals(2, result.get().size());
		assertEquals("Success 1", result.get().get(0));
		assertEquals("Success 2", result.get().get(1));
	}

	@Test
	public void testAggregateAll_error() {
		resultAggregator.add(successResult1);
		resultAggregator.add(errorResult);
		Result<List<Object>> result = resultAggregator.aggregateAll();
		assertTrue(result.isError());
	}

	@Test
	public void testAggregateAll_empty() {
		Result<List<Object>> result = resultAggregator.aggregateAll();
		assertTrue(result.isSuccess());
		assertEquals(0, result.get().size());
	}

	@Test
	public void testAggregateBut_success() {
		resultAggregator.add(successResult1);
		resultAggregator.add(successResult2);
		Result<String> result = resultAggregator.aggregateBut("Test");
		assertTrue(result.isSuccess());
		assertEquals("Test", result.get());
	}

	@Test
	public void testAggregateBut_error() {
		resultAggregator.add(successResult1);
		resultAggregator.add(errorResult);
		Result<String> result = resultAggregator.aggregateBut("Test");
		assertTrue(result.isError());
	}

	@Test
	public void testAggregateBut_empty() {
		Result<String> result = resultAggregator.aggregateBut("Test");
		assertTrue(result.isSuccess());
		assertEquals("Test", result.get());
	}

	@Test
	public void testAggregateButResult_success() {
		resultAggregator.add(successResult1);
		resultAggregator.add(successResult2);
		Result<String> result = resultAggregator.aggregateButResult(Result.success("Test"));
		assertTrue(result.isSuccess());
		assertEquals("Test", result.get());
	}

	@Test
	public void testAggregateButResult_error() {
		resultAggregator.add(successResult1);
		resultAggregator.add(errorResult);
		Result<String> result = resultAggregator.aggregateButResult(Result.success("Test"));
		assertTrue(result.isError());
	}

	@Test
	public void testAggregateButResult_empty() {
		Result<String> result = resultAggregator.aggregateButResult(Result.success("Test"));
		assertTrue(result.isSuccess());
		assertEquals("Test", result.get());
	}
}