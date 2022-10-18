package egecoskun121.com.crm.services;

import egecoskun121.com.crm.exception.NotFoundException;
import egecoskun121.com.crm.model.DTO.ComplaintDTO;
import egecoskun121.com.crm.model.entity.Complaint;
import egecoskun121.com.crm.model.mapper.ComplaintMapper;
import egecoskun121.com.crm.repositories.ComplaintRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
public class ComplaintService {

    private final ComplaintMapper complaintMapper;
    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintMapper complaintMapper, ComplaintRepository complaintRepository) {
        this.complaintMapper = complaintMapper;
        this.complaintRepository = complaintRepository;
    }

    public Complaint getComplaintById(Long id){
        return complaintRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Complaint saveNewComplaint(ComplaintDTO complaintDTO){
        return complaintRepository.save(complaintMapper.toComplaint(complaintDTO));
    }

    public Complaint updateComplaintById(ComplaintDTO complaintDTO,Long id){
        Complaint complaint = complaintRepository.findById(id).orElseThrow(NotFoundException::new);

        complaint.setComplaintSubject(complaintDTO.getComplaintSubject());
        complaint.setDetails(complaintDTO.getDetails());
        complaint.setAnswer(complaintDTO.getAnswer());
        complaint.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        return complaintRepository.save(complaint);
    }

    public void deleteComplaintById(Long id){
        complaintRepository.delete(complaintRepository.findById(id).orElseThrow(NotFoundException::new));
        double d = 22;
        long l = 62;

    }


}
