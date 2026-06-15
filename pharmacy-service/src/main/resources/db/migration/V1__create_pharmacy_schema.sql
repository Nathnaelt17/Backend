CREATE TABLE prescriptions (
    id UUID PRIMARY KEY,
    patient_id UUID NOT NULL,
    doctor_id UUID NOT NULL,
    hospital_id UUID NOT NULL,
    medication VARCHAR(255) NOT NULL,
    dosage VARCHAR(255) NOT NULL,
    issued_at TIMESTAMPTZ NOT NULL,
    status VARCHAR(32) NOT NULL
);
