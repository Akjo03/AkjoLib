package io.github.akjo03.lib.constructor;

@SuppressWarnings("unused")
public abstract class GenericConstructor<T extends Constructable, C extends GenericConstructor<T, C>> implements Constructor<T, C> {
    protected abstract C self();
}