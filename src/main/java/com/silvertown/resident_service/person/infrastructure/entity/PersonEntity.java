package com.silvertown.resident_service.person.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "person", indexes = {
        @Index(name = "idx_name_phone", columnList = "name, phone_number"),
        @Index(name = "idx_status", columnList = "status")
})
@Getter
@Setter
@NoArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false, updatable = false, columnDefinition = "INT COMMENT '사람 고유 ID'")
    private Integer personId;

    @Column(name = "name", nullable = false, length = 100, columnDefinition = "VARCHAR(100) COMMENT '이름'")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false, columnDefinition = "DATE COMMENT '생년월일 (YYYY-MM-DD 형식)'")
    private LocalDate birthDate;

    @Column(name = "phone_number", nullable = false, length = 20, columnDefinition = "VARCHAR(20) COMMENT '연락처'")
    private String phoneNumber;

    @Column(name = "email", length = 255, columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '비상 연락처 이메일'")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, columnDefinition = "ENUM('M', 'F', 'U') COMMENT '성별'")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('ACTIVE', 'INACTIVE', 'DELETED') DEFAULT 'ACTIVE' COMMENT '현재 상태 (정상, 비활성화, 삭제)'")
    private Status status;

    @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME DEFAULT now() COMMENT '생성일시'")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '갱신일시'")
    private LocalDateTime updatedAt;

    public enum Gender {
        M, F, U;
    }

    public enum Status {
        ACTIVE, INACTIVE, DELETED
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        this.updatedAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = Status.ACTIVE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}