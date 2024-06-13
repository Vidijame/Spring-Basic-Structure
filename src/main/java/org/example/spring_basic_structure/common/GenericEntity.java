package org.example.spring_basic_structure.common;

public abstract class GenericEntity<E, R> {
    public abstract void toEntity(E e);
    public abstract R toResponse();
}




