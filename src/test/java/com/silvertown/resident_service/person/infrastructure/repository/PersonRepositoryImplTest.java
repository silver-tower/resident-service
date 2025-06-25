package com.silvertown.resident_service.person.infrastructure.repository;


import com.silvertown.resident_service.person.infrastructure.entity.PersonEntity;
import com.silvertown.resident_service.person.infrastructure.entity.PersonEntity.Gender;
import com.silvertown.resident_service.person.infrastructure.entity.PersonEntity.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("PersonRepository")
class PersonRepositoryImplTest {

    @Autowired
    private TestEntityManager  entityManager;

    @Autowired
    private PersonJpaRepository personJpaRepository;

    @Test
    @DisplayName("전화번호로 사용자 존재 여부를 확인한다")
    void shouldCheckIfPersonExistsByPhoneNumber() {

        String phoneNumber = "01012345678";
        PersonEntity personEntity = PersonEntity.builder()
                .name("홍길동")
                .birthDate(LocalDate.of(1995, 1, 1))
                .phoneNumber(phoneNumber)
                .email("test@example.com")
                .gender(Gender.M)
                .status(Status.ACTIVE)
                .build();
        entityManager.persist(personEntity);

        boolean exists = personJpaRepository.existsByPhoneNumber(phoneNumber);

        assertTrue(exists, "저장된 전화번호로 존재 확인에 성공해야 합니다.");
    }

    @Test
    @DisplayName("데이터베이스에 없는 전화번호는 false를 반환한다")
    void shouldReturnFalseIfPhoneNumberDoesNotExist() {
        String nonExistentPhoneNumber = "01099999999";

        boolean exists = personJpaRepository.existsByPhoneNumber(nonExistentPhoneNumber);

        assertFalse(exists, "존재하지 않는 전화번호로 false 결과를 받아야 합니다.");
    }

    @Test
    @DisplayName("사용자를 데이터베이스에 저장할 수 있다")
    void shouldSavePersonToDatabase() {
        PersonEntity personEntity = PersonEntity.builder()
                .name("홍길동")
                .birthDate(LocalDate.of(1990, 5, 5))
                .phoneNumber("01098765432")
                .email("person@example.com")
                .gender(Gender.F)
                .status(Status.ACTIVE)
                .build();

        PersonEntity savedEntity = personJpaRepository.save(personEntity);

        assertNotNull(savedEntity.getPersonId(), "ID는 null일 수 없습니다.");
        assertEquals("홍길동", savedEntity.getName(), "이름이 일치해야 합니다.");
        assertEquals(LocalDate.of(1990, 5, 5), savedEntity.getBirthDate(), "생년월일이 일치해야 합니다.");
        assertEquals("01098765432", savedEntity.getPhoneNumber(), "전화번호가 일치해야 합니다.");
        assertEquals("person@example.com", savedEntity.getEmail(), "이메일이 일치해야 합니다.");
        assertEquals(Gender.F, savedEntity.getGender(), "성별이 일치해야 합니다.");
        assertEquals(Status.ACTIVE, savedEntity.getStatus(), "상태가 ACTIVE이어야 합니다.");
    }

    @Test
    @DisplayName("사용자를 ID로 조회할 수 있다")
    void shouldFindPersonById() {
        PersonEntity personEntity = PersonEntity.builder()
                .name("테스트 유저")
                .birthDate(LocalDate.of(1980, 4, 10))
                .phoneNumber("01056781234")
                .email("user@example.com")
                .gender(Gender.M)
                .status(Status.ACTIVE)
                .build();
        PersonEntity savedEntity = personJpaRepository.save(personEntity);

        Optional<PersonEntity> foundEntity = personJpaRepository.findById(savedEntity.getPersonId());

        assertTrue(foundEntity.isPresent(), "ID로 조회한 결과는 존재해야 합니다.");
        assertEquals(savedEntity.getPersonId(), foundEntity.get().getPersonId(), "조회된 ID가 저장된 사용자와 같아야 합니다.");
    }
}