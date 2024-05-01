package com.jestec.greendogdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jestec.greendogdelivery.model.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
