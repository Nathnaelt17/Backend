package com.tenalink.appointment.controller;
import com.tenalink.appointment.dto.AppointmentDto;
import com.tenalink.appointment.entity.AppointmentEntity;
import com.tenalink.appointment.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController @RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final AppointmentService service;
    public AppointmentController(AppointmentService service) { this.service = service; }
    @PostMapping public ResponseEntity<AppointmentEntity> create(@RequestBody AppointmentDto.CreateRequest req) { return ResponseEntity.ok(service.create(req)); }
    @GetMapping("/patient/{patientId}") public ResponseEntity<List<AppointmentEntity>> getByPatient(@PathVariable UUID patientId) { return ResponseEntity.ok(service.getByPatient(patientId)); }
    @PutMapping("/{id}/cancel") public ResponseEntity<Void> cancel(@PathVariable UUID id) { service.cancel(id); return ResponseEntity.ok().build(); }
}
