package com.example.demo.service;

import com.example.demo.utils.paging.Pageable;

import java.util.Collection;

public interface IServiceAdapter<T> {
    T create (T dto) throws Exception;
    T update(Long id, T dto) throws Exception;

    T getById(Long id);

    void deleteById(Long id) throws Exception;
    Collection<T> getAll();

    Collection<T> getAll(Pageable pageable);
}
