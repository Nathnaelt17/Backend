-- Fayda Health Records Platform — initial schema
-- Shared Primary Key: patients.user_id and doctors.user_id reference users.id

CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ─── Core auth identity ───────────────────────────────────────────────────

CREATE TABLE users (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email           VARCHAR(255) UNIQUE,
    fayda_id        CHAR(12) UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,
    full_name       VARCHAR(255) NOT NULL,
    role            VARCHAR(32)  NOT NULL
                    CHECK (role IN ('PATIENT', 'DOCTOR', 'HOSPITAL_ADMIN', 'SUPER_ADMIN')),
    created_at      TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    CONSTRAINT users_login_identity CHECK (email IS NOT NULL OR fayda_id IS NOT NULL)
);

CREATE INDEX idx_users_email ON users (email) WHERE email IS NOT NULL;
CREATE INDEX idx_users_fayda_id ON users (fayda_id) WHERE fayda_id IS NOT NULL;

-- ─── Hospital master (facility anchor) ──────────────────────────────────────

CREATE TABLE hospitals (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name                VARCHAR(255) NOT NULL,
    address             TEXT         NOT NULL,
    region              VARCHAR(100),
    fayda_facility_code VARCHAR(32) UNIQUE,
    created_at          TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);

-- ─── Role profiles (Shared PK with users) ───────────────────────────────────

CREATE TABLE patients (
    user_id         UUID PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    date_of_birth   DATE,
    phone_number    VARCHAR(20)
);

CREATE TABLE doctors (
    user_id         UUID PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    hospital_id     UUID NOT NULL REFERENCES hospitals (id),
    specialty       VARCHAR(100) NOT NULL,
    license_number  VARCHAR(50)  NOT NULL UNIQUE
);

CREATE INDEX idx_doctors_hospital ON doctors (hospital_id);

-- ─── Appointments (patient-owned requests) ──────────────────────────────────

CREATE TABLE appointments (
    id                    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id            UUID NOT NULL REFERENCES patients (user_id),
    doctor_id             UUID REFERENCES doctors (user_id),
    hospital_id           UUID NOT NULL REFERENCES hospitals (id),
    requested_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    scheduled_at          TIMESTAMPTZ,
    status                VARCHAR(32) NOT NULL DEFAULT 'REQUESTED'
                          CHECK (status IN ('REQUESTED', 'APPROVED', 'REJECTED',
                                            'RESCHEDULED', 'COMPLETED', 'CANCELLED')),
    patient_notes         TEXT,
    doctor_response_notes TEXT
);

CREATE INDEX idx_appointments_patient  ON appointments (patient_id);
CREATE INDEX idx_appointments_doctor   ON appointments (doctor_id);
CREATE INDEX idx_appointments_hospital ON appointments (hospital_id);
CREATE INDEX idx_appointments_status   ON appointments (status);

-- ─── Prescriptions (hospital-anchored) ──────────────────────────────────────

CREATE TABLE prescriptions (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id   UUID NOT NULL REFERENCES patients (user_id),
    doctor_id    UUID NOT NULL REFERENCES doctors (user_id),
    hospital_id  UUID NOT NULL REFERENCES hospitals (id),
    medication   VARCHAR(255) NOT NULL,
    dosage       VARCHAR(100) NOT NULL,
    instructions TEXT,
    status       VARCHAR(32) NOT NULL DEFAULT 'ACTIVE'
                 CHECK (status IN ('ACTIVE', 'DISPENSED', 'EXPIRED', 'CANCELLED')),
    issued_at    TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_prescriptions_patient  ON prescriptions (patient_id);
CREATE INDEX idx_prescriptions_hospital ON prescriptions (hospital_id);

-- ─── Medical events timeline (append-only, hospital-anchored) ───────────────

CREATE TABLE medical_events (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id  UUID NOT NULL REFERENCES patients (user_id),
    hospital_id UUID NOT NULL REFERENCES hospitals (id),
    doctor_id   UUID NOT NULL REFERENCES doctors (user_id),
    timestamp   TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    event_type  VARCHAR(64) NOT NULL
                CHECK (event_type IN ('DIAGNOSIS', 'LAB_RESULT', 'PRESCRIPTION_ISSUED',
                                      'APPOINTMENT_COMPLETED', 'VITAL_SIGNS', 'CLINICAL_NOTE')),
    event_data  TEXT NOT NULL
);

CREATE INDEX idx_medical_events_patient_hospital ON medical_events (patient_id, hospital_id);
CREATE INDEX idx_medical_events_doctor           ON medical_events (doctor_id);
CREATE INDEX idx_medical_events_timestamp        ON medical_events (patient_id, timestamp);
