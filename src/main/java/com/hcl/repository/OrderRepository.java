package com.hcl.repository;

import org.springframework.data.repository.CrudRepository;

import com.hcl.model.SalesOrder;

public interface OrderRepository extends CrudRepository<SalesOrder, Integer>{

}
