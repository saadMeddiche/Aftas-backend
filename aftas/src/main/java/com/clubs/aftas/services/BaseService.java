package com.clubs.aftas.services;

import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.handlingExceptions.costumExceptions.DoNotExistException;
import com.clubs.aftas.handlingExceptions.costumExceptions.EmptyException;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.*;


public class BaseService<T, ID> {

    protected JpaRepository<T, ID> repository;

    private  Class<T> entityClass;


    public BaseService(JpaRepository<T, ID> repository , Class<T> entityClass){
        this.repository = repository;
        this.entityClass = entityClass;
    }

    public List<T> getAllEntities() {
        return Optional.of(repository.findAll()).filter(entities -> !entities.isEmpty())
                .orElseThrow(() -> new EmptyException("No "+ entityClass.getSimpleName()+"s have been added yet"));
    }

    public Page<T> getAllEntitiesWithPagination(Pageable pageable) {
        return Optional.of(repository.findAll(pageable)).filter(entities -> !entities.isEmpty())
                .orElseThrow(() -> new EmptyException("No "+entityClass.getSimpleName()+"s have been found"));
    }

    public T getEntityById(ID id) {
        return repository.findById(id).orElseThrow(() -> new DoNotExistException("No "+ entityClass.getSimpleName()+" has been found with id: " + id));
    }

    public void deleteEntityById(ID id){
        Optional.of(repository.findById(id)).filter(Optional::isPresent).orElseThrow(() -> new DoNotExistException("No "+ entityClass.getSimpleName()+" has been found with id: " + id));
        repository.deleteById(id);
    }


    public <T> Specification<T> searchByCriteria(List<FilterDTO> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters != null && !filters.isEmpty()) {
                for (FilterDTO filter : filters) {
                    String columnName = filter.getColumnName();
                    Object columnValue = filter.getColumnValue();

                    if (isValidColumn(entityClass, columnName)) {
                        String likeSearchTerm = "%" + columnValue.toString().toLowerCase() + "%";
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(columnName)), likeSearchTerm));
                    }
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public <T> Specification<T> search(String searchValue) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (searchValue == null || searchValue.isEmpty()) {
                    return criteriaBuilder.conjunction();
                }

                List<Predicate> predicates = new ArrayList<>();

                for (Field field: entityClass.getDeclaredFields()) {

                    String attributeName = field.getName();

                    // https://discourse.hibernate.org/t/criteriabuilder-cast-function-example/6631

                    if (List.class.isAssignableFrom(field.getType())) {
                        continue;
                    }

                    System.out.printf("Field Type :" + root.get(attributeName).getJavaType());

                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(attributeName).as(String.class)), "%" + searchValue.toLowerCase() + "%"));



//                    if(field.getType().equals(String.class)) {
//                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(attributeName)), "%" + searchValue.toLowerCase() + "%"));
//                    }
//
//                   if(field.getType().equals(Integer.class)) {
//                        predicates.add(criteriaBuilder.equal(root.get(attributeName),searchValue));
//                   }
//
//                    if(field.getType().equals(Double.class) ) {
//                        predicates.add(criteriaBuilder.like(root.get(attributeName), searchValue ));
//                    }


                }

                Predicate[] predicatess = predicates.toArray(new Predicate[0]);

                return criteriaBuilder.or(predicatess);
            }
        };
    }

    private static <T> boolean isValidColumn(Class<T> entityClass, String columnName) {
        try {
            Field field = entityClass.getDeclaredField(columnName);
            return field != null;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}
