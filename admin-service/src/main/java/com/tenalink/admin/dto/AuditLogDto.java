package com.tenalink.admin.dto;
import lombok.Data;
import java.util.UUID;
public class AuditLogDto {
    @Data public static class CreateRequest { private UUID adminId; private String action; private String targetResource; }
}
