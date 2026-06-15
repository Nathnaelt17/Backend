# TenaLink API

Gateway base URL: `http://localhost:8080`

## Routes

- `POST /api/v1/auth/register`
- `POST /api/v1/auth/login`
- `POST /api/v1/auth/refresh`
- `GET /api/v1/auth/me`
- `GET|POST /api/v1/users/**`
- `GET|POST /api/v1/records/**`
- `GET|POST|PATCH /api/v1/appointments/**`
- `GET|POST /api/v1/pharmacy/**`
- `GET|POST /api/v1/admin/**`

All protected services accept `Authorization: Bearer <jwt>`. Medical record events and audit logs are append-only.
