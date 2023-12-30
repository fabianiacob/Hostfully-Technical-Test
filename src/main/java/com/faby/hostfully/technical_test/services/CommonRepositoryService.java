package com.faby.hostfully.technical_test.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class CommonRepositoryService<E, R extends JpaRepository<E, Long>> {
    protected final R repository;

    public CommonRepositoryService(R repository) {
        this.repository = repository;
    }

    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    public E getReferenceById(Long id) {
        return repository.getReferenceById(id);
    }

    public E save(E entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }
}
