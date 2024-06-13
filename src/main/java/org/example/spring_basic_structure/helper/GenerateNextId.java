package org.example.spring_basic_structure.helper;

import jdk.jfr.Name;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.security.SecureRandom;

@Component
public class GenerateNextId implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        Class<?> clazz = object.getClass();
        Name annotation = clazz.getAnnotation(Name.class);
        return generateNextVal(annotation.value());
    }

    public static String generateNextVal(String prefix) {
        final SecureRandom random = new SecureRandom();
        long randomNumber = Math.abs(random.nextLong() % 100000000L);
        return prefix + "_" + randomNumber;
    }
}
