package br.com.academy.luizpedro.mercadolivre.controller.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
public class ExistIdValidator implements ConstraintValidator<ExistId, Object> {
    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistId constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();
        return !list.isEmpty() || o == null;
    }
}