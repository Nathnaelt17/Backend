package com.tenalink.medicalrecords.service;
import com.tenalink.medicalrecords.dto.MedicalEventDto;
import com.tenalink.medicalrecords.entity.MedicalEventEntity;
import com.tenalink.medicalrecords.repository.MedicalEventRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class MedicalEventService {
    private final MedicalEventRepository repo;
    public MedicalEventService(MedicalEventRepository repo) { this.repo = repo; }
    public MedicalEventEntity create(MedicalEventDto.CreateRequest req) {
        MedicalEventEntity e = new MedicalEventEntity();
        e.setPatientId(req.getPatientId()); e.setHospitalId(req.getHospitalId());
        e.setAuthorId(req.getAuthorId()); e.setTimestamp(req.getTimestamp());
        e.setEventType(req.getEventType()); e.setEventData(req.getEventData());
        return repo.save(e);
    }
    public List<MedicalEventEntity> getTimeline(UUID patientId) { return repo.findByPatientIdOrderByTimestampDesc(patientId); }
    public List<MedicalEventEntity> getDocuments(UUID patientId) { return repo.findByPatientIdAndEventTypeOrderByTimestampDesc(patientId, "DOCUMENT"); }
}
