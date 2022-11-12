package egecoskun121.com.crm.services;

import egecoskun121.com.crm.exception.NotFoundException;
import egecoskun121.com.crm.model.DTO.ComplaintDTO;
import egecoskun121.com.crm.model.entity.Complaint;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.model.mapper.ComplaintMapper;
import egecoskun121.com.crm.repositories.ComplaintRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
public class ComplaintService {

    private final ComplaintMapper complaintMapper;
    private final ComplaintRepository complaintRepository;

    private final UserService userService;


    public ComplaintService(ComplaintMapper complaintMapper, ComplaintRepository complaintRepository, UserService userService) {
        this.complaintMapper = complaintMapper;
        this.complaintRepository = complaintRepository;
        this.userService = userService;
    }

    public Complaint getComplaintById(Long id){
        return complaintRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Complaint> getAllComplaints(){
        return complaintRepository.findAll();
    }

    public List<Complaint> getAllComplaintsByUsername(String username){
        List<Complaint> allComplaintsByName = complaintRepository.findAllComplaintsByUsername(username);
        return allComplaintsByName;
    }

    public Complaint saveNewComplaint(ComplaintDTO complaintDTO,String username){
        Complaint complaint = complaintMapper.toComplaint(complaintDTO);
        User user = userService.getUserByUsername(username);
        complaint.setUser(user);
        user.getComplaints().add(complaint);
        return complaintRepository.save(complaint);
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
    }

}
