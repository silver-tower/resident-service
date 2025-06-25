package com.silvertown.resident_service.person.infrastructure.repository;

import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.domain.model.Person;
import com.silvertown.resident_service.person.domain.repository.PersonRepository;
import com.silvertown.resident_service.person.infrastructure.PersonMapper;
import com.silvertown.resident_service.person.infrastructure.entity.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {
    private final PersonJpaRepository personJpaRepository;
    private final PersonMapper personMapper;

    @Override
    public boolean existsByPhoneNumber(Phone phone) {
        return personJpaRepository.existsByPhoneNumber(phone.phone());
    }

    @Override
    public Person save(Person person) {
        PersonEntity entity = personMapper.toEntity(person);
        PersonEntity savedEntity = personJpaRepository.save(entity);
        return personMapper.toDomain(savedEntity);
    }
}
