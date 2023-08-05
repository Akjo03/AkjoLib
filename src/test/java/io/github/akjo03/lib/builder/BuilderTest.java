package io.github.akjo03.lib.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

public class BuilderTest {
	@Test
	@SuppressWarnings("unchecked")
	public void testBuilderBuildsBuildable() {
		Builder<Buildable> builder = mock(Builder.class);
		Buildable buildable = mock(Buildable.class);

		when(builder.build()).thenReturn(buildable);

		Buildable result = builder.build();

		assertSame(buildable, result);
		verify(builder).build();
	}
}