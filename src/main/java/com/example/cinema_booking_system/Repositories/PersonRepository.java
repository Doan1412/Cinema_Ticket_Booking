package com.example.cinema_booking_system.Repositories;

import com.example.cinema_booking_system.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
