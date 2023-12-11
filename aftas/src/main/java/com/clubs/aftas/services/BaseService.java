package com.clubs.aftas.services;

import com.clubs.aftas.handlingExceptions.costumExceptions.DoNotExistException;
import com.clubs.aftas.handlingExceptions.costumExceptions.EmptyException;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



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
}
