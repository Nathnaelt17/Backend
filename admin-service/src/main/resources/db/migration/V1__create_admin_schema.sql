CREATE TABLE audit_logs (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    role VARCHAR(32) NOT NULL,
    hospital_id UUID NOT NULL,
    timestamp TIMESTAMPTZ NOT NULL,
    action_type VARCHAR(64) NOT NULL,
    target_record_id UUID NOT NULL
);
