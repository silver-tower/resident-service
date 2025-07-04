package com.silvertown.resident_service.person.domain.model;

import com.silvertown.resident_service.common.vo.Email;
import com.silvertown.resident_service.common.vo.Name;
import com.silvertown.resident_service.common.vo.Phone;
import com.silvertown.resident_service.person.domain.model.vo.BirthDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;
    private Name name;
    private BirthDate birthDate;
    private Phone phoneNumber;
    private PersonStatus status;
    private Gender gender;
    private Email email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void changeStatusTo(PersonStatus status) {
        if (this.status.canTransitionTo(status)) {
            this.status = status;
        }
    }
}
