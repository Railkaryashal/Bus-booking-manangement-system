package com.cts.projectBus.ProBus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.projectBus.ProBus.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
