package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Orderdetail;

public interface OrderdetailRepository extends JpaRepository<Orderdetail, Integer>{

}
