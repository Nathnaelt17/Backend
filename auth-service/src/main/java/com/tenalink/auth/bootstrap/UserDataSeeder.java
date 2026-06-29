package com.tenalink.auth.bootstrap;

import com.tenalink.auth.entity.UserEntity;
import com.tenalink.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserDataSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UserDataSeeder.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JdbcTemplate userDbJdbcTemplate;

    public UserDataSeeder(UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder,
                          @Qualifier("userDbJdbcTemplate") JdbcTemplate userDbJdbcTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDbJdbcTemplate = userDbJdbcTemplate;
    }

    @Override
    public void run(String... args) {
        logger.info("Starting Auth user data seeding...");
        createAdminIfMissing("admin@tenalink.com", "admin123", "ROLE_ADMIN", "System Admin");
        createProviderIfMissing("doctor@tenalink.com", "doctor123", "ROLE_PROVIDER", "Default Provider");
        createPatientIfMissing("patient@tenalink.com", "patient123", "ROLE_PATIENT", "Default Patient");
        createAdminIfMissing("super@tenalink.com", "super123", "ROLE_SUPER_ADMIN", "System Super Admin");
        ensureAllPatientRecordsExist();
        logger.info("Auth user data seeding complete.");
    }

    /**
     * Ensure every ROLE_PATIENT user has a matching patient record.
     * This fixes the case where users were seeded before patient-record logic existed.
     */
    private void ensureAllPatientRecordsExist() {
        try {
            java.util.List<UserEntity> patientUsers = userRepository.findByRole("ROLE_PATIENT");
            for (UserEntity user : patientUsers) {
                Integer count = userDbJdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM patients WHERE user_id = ?",
                        Integer.class,
                        user.getId()
                );
                if (count == null || count == 0) {
                    UUID patientId = UUID.randomUUID();
                    userDbJdbcTemplate.update(
                            "INSERT INTO patients (id, user_id, fayda_id, full_name, date_of_birth, gender, contact_phone) VALUES (?, ?, ?, ?, ?, ?, ?)",
                            patientId, user.getId(),
                            user.getFaydaId() != null ? user.getFaydaId() : "FAYDA" + user.getId().toString().substring(0, 8).toUpperCase(),
                            user.getFullName(), "1990-01-01", "UNKNOWN", "+000000000"
                    );
                    logger.info("Created missing patient record {} for existing user {}", patientId, user.getId());
                }
            }
        } catch (Exception e) {
            logger.warn("Could not verify patient records: {}", e.getMessage());
        }
    }

    private void createAdminIfMissing(String email, String rawPassword, String role, String fullName) {
        if (userRepository.findByEmail(email).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setFaydaId(null);
            user.setPasswordHash(passwordEncoder.encode(rawPassword));
            user.setFullName(fullName);
            user.setRole(normalizeRole(role));
            userRepository.save(user);
            logger.info("Seeded admin user {} with role {}", email, user.getRole());
        } else {
            logger.info("Admin user {} already exists; skipping seed.", email);
        }
    }

    private void createProviderIfMissing(String email, String rawPassword, String role, String fullName) {
        if (userRepository.findByEmail(email).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setFaydaId(null);
            user.setPasswordHash(passwordEncoder.encode(rawPassword));
            user.setFullName(fullName);
            user.setRole(normalizeRole(role));
            userRepository.save(user);

            // Create doctor record in user_db
            try {
                UUID doctorId = UUID.randomUUID();
                userDbJdbcTemplate.update(
                        "INSERT INTO doctors (id, user_id, full_name, specialization, contact_phone) VALUES (?, ?, ?, ?, ?)",
                        doctorId, user.getId(), fullName, "General Practice", "+000000000"
                );
                logger.info("Seeded doctor record {} for user {}", doctorId, user.getId());
            } catch (Exception e) {
                logger.warn("Could not create doctor record for user {}: {}. Table may not exist yet.", email, e.getMessage());
            }

            logger.info("Seeded provider user {} with role {}", email, user.getRole());
        } else {
            logger.info("Provider user {} already exists; skipping seed.", email);
        }
    }

    private void createPatientIfMissing(String email, String rawPassword, String role, String fullName) {
        if (userRepository.findByEmail(email).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setFaydaId(null);
            user.setPasswordHash(passwordEncoder.encode(rawPassword));
            user.setFullName(fullName);
            user.setRole(normalizeRole(role));
            userRepository.save(user);

            // Create patient record in user_db
            try {
                UUID patientId = UUID.randomUUID();
                userDbJdbcTemplate.update(
                        "INSERT INTO patients (id, user_id, fayda_id, full_name, date_of_birth, gender, contact_phone) VALUES (?, ?, ?, ?, ?, ?, ?)",
                        patientId, user.getId(), "FAYDA000001", fullName, "1990-01-01", "UNKNOWN", "+000000000"
                );
                logger.info("Seeded patient record {} for user {}", patientId, user.getId());
            } catch (Exception e) {
                logger.warn("Could not create patient record for user {}: {}. Table may not exist yet.", email, e.getMessage());
            }

            logger.info("Seeded patient user {} with role {}", email, user.getRole());
        } else {
            logger.info("Patient user {} already exists; skipping seed.", email);
        }
    }

    private String normalizeRole(String role) {
        if (role == null || role.isBlank()) return "ROLE_PATIENT";
        String normalized = role.trim().toUpperCase();
        return normalized.startsWith("ROLE_") ? normalized : "ROLE_" + normalized;
    }
}
