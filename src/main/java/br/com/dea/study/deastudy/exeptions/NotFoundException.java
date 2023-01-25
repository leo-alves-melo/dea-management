package br.com.dea.study.deastudy.exeptions;

import java.util.List;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> classType, long id) {
        super(String.format("Entity %s with id %d not found", classType.getSimpleName(), id));
    }

    public NotFoundException(Class<?> classType, List<Long> ids) {
        super(String.format("Entity %s with id %s not found", classType.getSimpleName(), ids));
    }

    public NotFoundException(Class<?> classType, String id) {
        super(String.format("Entity %s with id %s not found", classType.getSimpleName(), id));
    }

}