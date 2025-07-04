package com.silvertown.resident_service.person.infrastructure.repository;

import com.silvertown.resident_service.person.infrastructure.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface PersonJpaRepository extends JpaRepository<PersonEntity, Integer> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PersonEntity p WHERE p.phoneNumber = :phoneNumber AND p.status <> 'DELETED'")
    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT p FROM PersonEntity p WHERE p.personId = :personId AND p.status <> 'DELETED'")
    Optional<PersonEntity> findByPersonId(Integer personId);
}
