package com.silvertown.resident_service.resident.domain;

import com.silvertown.resident_service.person.domain.model.Person;
import com.silvertown.resident_service.residence.domain.model.EmergencyContact;
import com.silvertown.resident_service.residence.domain.model.Residence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Resident")
class ResidentTest {

    @Test
    @DisplayName("Resident 객체를 정상적으로 생성")
    void validResidentShouldBeCreated() {
        Person person = new Person();
        Residence residence = new Residence();
        List<EmergencyContact> contacts = List.of(new EmergencyContact());

        Resident resident = new Resident(person, residence, contacts, new Date(), null, "No notes");

        assertNotNull(resident);
        assertEquals(person, resident.getPerson());
        assertEquals(residence, resident.getResidence());
        assertEquals(ResidentStatus.PENDING, resident.getResidentStatus());
    }

    @Test
    @DisplayName("필수 필드가 없는 경우 Error 발생")
    void missingRequiredFieldsShouldThrowException() {
        Person person = new Person();
        Residence residence = new Residence();

        assertThrows(Exception.class, () -> new Resident(null, residence, null, new Date(), null, null));
        assertThrows(Exception.class, () -> new Resident(person, null, null, new Date(), null, null));
    }

    @Test
    @DisplayName("상태 전환이 가능한 경우 상태 변경")
    void shouldChangeStatusIfTransitionIsValid() {
        Person person = new Person();
        Residence residence = new Residence();
        List<EmergencyContact> contacts = List.of(new EmergencyContact());

        Resident resident = new Resident(person, residence, contacts, new Date(), null, "No notes");
        resident.changeStatusTo(ResidentStatus.APPROVED);

        assertEquals(ResidentStatus.APPROVED, resident.getResidentStatus());
    }

    @Test
    @DisplayName("상태 전환이 불가능한 경우 상태 변경되지 않음")
    void shouldNotChangeStatusIfTransitionIsInvalid() {
        Person person = new Person();
        Residence residence = new Residence(

        );
        List<EmergencyContact> contacts = List.of(new EmergencyContact());

        Resident resident = new Resident(person, residence, contacts, new Date(), null, "No notes");
        resident.changeStatusTo(ResidentStatus.APPROVED);
        resident.changeStatusTo(ResidentStatus.TERMINATED);
        resident.changeStatusTo(ResidentStatus.APPROVED);

        assertEquals(ResidentStatus.TERMINATED, resident.getResidentStatus());
    }
}
