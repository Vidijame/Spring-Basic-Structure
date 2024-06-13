package org.example.spring_basic_structure.common;

public interface GenericEnum<T, E> {
    E getValue();
    String getLabel();
}

