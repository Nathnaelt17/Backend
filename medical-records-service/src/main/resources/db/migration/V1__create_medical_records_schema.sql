CREATE TABLE medical_events (
    id UUID PRIMARY KEY,
    patient_id UUID NOT NULL,
    hospital_id UUID NOT NULL,
    author_id UUID NOT NULL,
    timestamp TIMESTAMPTZ NOT NULL,
    event_type VARCHAR(64) NOT NULL CHECK (event_type IN (
        'VISIT_CREATED',
        'DIAGNOSIS_ADDED',
        'PRESCRIPTION_ISSUED',
        'LAB_RESULT_UPLOADED',
        'DOCTOR_NOTE_ADDED',
        'SYSTEM_EVENT'
    )),
    event_data TEXT NOT NULL
);

CREATE TABLE audit_logs (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    role VARCHAR(32) NOT NULL,
    hospital_id UUID NOT NULL,
    timestamp TIMESTAMPTZ NOT NULL,
    action_type VARCHAR(128) NOT NULL,
    target_record_id UUID NOT NULL
);
