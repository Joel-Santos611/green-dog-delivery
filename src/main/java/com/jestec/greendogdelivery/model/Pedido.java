package com.jestec.greendogdelivery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
public class Pedido implements Serializable{

@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne(optional = true)
 private Cliente cliente;

 @ManyToMany
 @Cascade(CascadeType.MERGE)
 private List<Item> itens=new ArrayList<Item>();

 @DateTimeFormat(pattern = "dd-MM-yy")
 private Date data;

 @Min(1)
 private Double valorTotal;


 
public Pedido(Long id, Cliente cliente, List<Item> itens, Date data, Double valorTotal) {
    this.id = id;
    this.cliente = cliente;
    this.itens = itens;
    this.data = data;
    this.valorTotal = valorTotal;
}



public Pedido(Long id, Cliente cliente, List<Item> itens, @Min(1) Double valorTotal) {
    this.id = id;
    this.cliente = cliente;
    this.itens = itens;
    this.valorTotal = valorTotal;
}



public Pedido() {
}



@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
}


@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Pedido other = (Pedido) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    return true;
}



public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public Cliente getCliente() {
    return cliente;
}
public void setCliente(Cliente cliente) {
    this.cliente = cliente;
}
public List<Item> getItens() {
    return itens;
}
public void setItens(List<Item> itens) {
    this.itens = itens;
}
public Date getData() {
    return data;
}
public void setData(Date data) {
    this.data = data;
}
public Double getValorTotal() {
    return valorTotal;
}
public void setValorTotal(Double valorTotal) {
    this.valorTotal = valorTotal;
}


 
}