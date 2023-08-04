package io.github.akjo03.lib.collection;

import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class FilteringSet<K> extends AbstractSet<K> {
	private final Set<K> original;
	private final Set<K> filters;
	private final int size;

	private FilteringSet(Set<K> original, Set<K> filters, int size) {
		this.original = ImmutableSet.copyOf(original);
		this.filters = ImmutableSet.copyOf(filters);
		this.size = size;
	}

	@Contract("_, _ -> new")
	public static <K> @NotNull FilteringSet<K> of(@NotNull Set<K> original, @NotNull Set<K> filters) {
		if (!original.containsAll(filters)) {
			throw new IllegalArgumentException("FilteringSet filters elements from the original set, filters should not contain elements not in the original set.");
		}
		return new FilteringSet<>(original, filters, original.size() - filters.size());
	}

	@Override
	public Iterator<K> iterator() {
		return new Iterator<>() {
			private final Iterator<K> originalIterator = original.iterator();
			private K next = advance();

			private @Nullable K advance() {
				while (originalIterator.hasNext()) {
					K next = originalIterator.next();
					if (isElementVisible(next)) {
						return next;
					}
				}
				return null;
			}

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public K next() {
				if (next == null) {
					throw new NoSuchElementException();
				}
				K result = next;
				next = advance();
				return result;
			}
		};
	}

	private boolean isElementVisible(K k) {
		return k != null && original.contains(k) && !filters.contains(k);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void forEach(Consumer<? super K> action) {
		for (K k : original) {
			if (isElementVisible(k)) {
				action.accept(k);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean contains(Object o) {
		try {
			K k = (K) o;
			return isElementVisible(k);
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size()];
		int i = 0;
		for (K k : this) { array[i++] = k; }
		return array;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T @NotNull [] a) {
		if (a.length < size()) {
			return (T[]) toArray();
		}
		int i = 0;
		for (K k : this) { a[i++] = (T) k; }
		return a;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object object) {
		if (object == this) { return true; }
		if (!(object instanceof Set<?> set)) { return false; }
		if (set.size() != size()) { return false; }

		try {
			Set<K> otherSet = (Set<K>) object;
			return otherSet.stream().allMatch(this::isElementVisible);
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return stream().mapToInt(k -> 31 * 17 + k.hashCode()).sum();
	}

	@Override
	public String toString() {
		return stream().map(Objects::toString).collect(Collectors.joining(", ", "{", "}"));
	}

	@Override
	public Stream<K> stream() {
		return original.stream().filter(this::isElementVisible);
	}

	@Override
	public Stream<K> parallelStream() {
		return original.parallelStream().filter(this::isElementVisible);
	}
}
