package egecoskun121.com.crm.model.mapper;

import egecoskun121.com.crm.model.DTO.ComplaintDTO;
import egecoskun121.com.crm.model.entity.Complaint;
import org.mapstruct.Mapper;

@Mapper
public interface ComplaintMapper {
    ComplaintDTO toComplaintDTO(Complaint complaint);
    Complaint toComplaint(ComplaintDTO complaintDTO);
}
