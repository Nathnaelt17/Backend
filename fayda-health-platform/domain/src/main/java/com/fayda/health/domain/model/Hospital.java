package com.fayda.health.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Hospital {

    private final UUID id;
    private final String name;
    private final String address;
    private final String region;
    private final String faydaFacilityCode;

    public Hospital(UUID id, String name, String address, String region, String faydaFacilityCode) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.address = Objects.requireNonNull(address);
        this.region = region;
        this.faydaFacilityCode = faydaFacilityCode;
    }

    public static Hospital create(String name, String address, String region, String faydaFacilityCode) {
        return new Hospital(UUID.randomUUID(), name, address, region, faydaFacilityCode);
    }

    public static Hospital restore(UUID id, String name, String address, String region, String faydaFacilityCode) {
        return new Hospital(id, name, address, region, faydaFacilityCode);
    }

    public UUID id() { return id; }
    public String name() { return name; }
    public String address() { return address; }
    public String region() { return region; }
    public String faydaFacilityCode() { return faydaFacilityCode; }
}
