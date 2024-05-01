package com.jestec.greendogdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jestec.greendogdelivery.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    
}
