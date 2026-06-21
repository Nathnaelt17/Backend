package com.tenalink.user.dto;
import lombok.Data;
public class PatientDto {
    @Data public static class UpsertRequest { private String faydaId; private String fullName; private String dateOfBirth; private String gender; private String contactPhone; }
}
