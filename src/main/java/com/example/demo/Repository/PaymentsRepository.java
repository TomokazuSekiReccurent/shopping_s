package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Payments;

public interface PaymentsRepository  extends JpaRepository<Payments, Integer> {

}
