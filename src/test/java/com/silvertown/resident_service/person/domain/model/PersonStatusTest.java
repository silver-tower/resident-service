package com.silvertown.resident_service.person.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PersonStatus")
class PersonStatusTest {

    @Test
    @DisplayName("ACTIVE 상태는 INACTIVE와 DELETED로 전환 가능해야 한다")
    void shouldTransitionFromActiveToInactiveOrDeleted() {

        PersonStatus currentStatus = PersonStatus.ACTIVE;

        assertTrue(currentStatus.canTransitionTo(PersonStatus.INACTIVE), "ACTIVE -> INACTIVE 전환 가능");
        assertTrue(currentStatus.canTransitionTo(PersonStatus.DELETED), "ACTIVE -> DELETED 전환 가능");

        assertFalse(currentStatus.canTransitionTo(PersonStatus.ACTIVE), "ACTIVE -> ACTIVE 전환 불가능");
    }

    @Test
    @DisplayName("INACTIVE 상태는 ACTIVE와 DELETED로 전환 가능해야 한다")
    void shouldTransitionFromInactiveToActiveOrDeleted() {

        PersonStatus currentStatus = PersonStatus.INACTIVE;

        assertTrue(currentStatus.canTransitionTo(PersonStatus.ACTIVE), "INACTIVE -> ACTIVE 전환 가능");
        assertTrue(currentStatus.canTransitionTo(PersonStatus.DELETED), "INACTIVE -> DELETED 전환 가능");

        assertFalse(currentStatus.canTransitionTo(PersonStatus.INACTIVE), "INACTIVE -> INACTIVE 전환 불가능");
    }

    @Test
    @DisplayName("DELETED 상태는 어떤 상태로도 전환할 수 없어야 한다")
    void shouldNotAllowAnyTransitionFromDeleted() {

        PersonStatus currentStatus = PersonStatus.DELETED;

        assertFalse(currentStatus.canTransitionTo(PersonStatus.ACTIVE), "DELETED -> ACTIVE 전환 불가능");
        assertFalse(currentStatus.canTransitionTo(PersonStatus.INACTIVE), "DELETED -> INACTIVE 전환 불가능");
        assertFalse(currentStatus.canTransitionTo(PersonStatus.DELETED), "DELETED -> DELETED 전환 불가능");
    }
}
