package br.com.reinaldo.pucgraphql.exceptions;

public class EntityNotFound extends RuntimeException {

    public EntityNotFound(Integer entityId, Class<?> entityClass) {
        super(String.format("Entity with id %d not found for class %s", entityId, entityClass.getSimpleName()));
    }
}
