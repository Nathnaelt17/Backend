package com.tenalink.appointment.service;
import com.tenalink.appointment.dto.AppointmentDto;
import com.tenalink.appointment.entity.AppointmentEntity;
import com.tenalink.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class AppointmentService {
    private final AppointmentRepository repo;
    public AppointmentService(AppointmentRepository repo) { this.repo = repo; }
    public AppointmentEntity create(AppointmentDto.CreateRequest req) {
        AppointmentEntity a = new AppointmentEntity();
        a.setPatientId(req.getPatientId()); a.setDoctorId(req.getDoctorId());
        a.setHospitalId(req.getHospitalId()); a.setScheduledAt(req.getScheduledAt());
        a.setStatus("SCHEDULED");
        return repo.save(a);
    }
    public List<AppointmentEntity> getByPatient(UUID patientId) { return repo.findByPatientId(patientId); }
    public void cancel(UUID id) {
        AppointmentEntity a = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        a.setStatus("CANCELLED");
        repo.save(a);
    }
}
