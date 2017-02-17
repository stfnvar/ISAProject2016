package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.restaurant.model.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {

}

