package com.tenalink.user.dto;
import lombok.Data;
import java.util.UUID;
public class PatientDto {
    @Data public static class UpsertRequest {
        private String faydaId;
        private String fullName;
        private String dateOfBirth;
        private String gender;
        private String contactPhone;
        private String bloodType;
        private String allergies;
        private String chronicConditions;
    }
    @Data public static class Response {
        private UUID id;
        private UUID userId;
        private String faydaId;
        private String fullName;
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private String gender;
        private String contactPhone;
        private String bloodType;
        private String allergies;
        private String chronicConditions;
    }
}
