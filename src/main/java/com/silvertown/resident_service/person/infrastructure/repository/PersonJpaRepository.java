package com.silvertown.resident_service.person.infrastructure.repository;

import com.silvertown.resident_service.person.infrastructure.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PersonJpaRepository  extends JpaRepository<PersonEntity, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}

