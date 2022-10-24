package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.ComplaintDTO;
import egecoskun121.com.crm.model.DTO.ProductInquiryDTO;
import egecoskun121.com.crm.model.mapper.ComplaintMapperImpl;
import egecoskun121.com.crm.services.ComplaintService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("api/v1/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final ComplaintMapperImpl complaintMapperImpl;

    public ComplaintController(ComplaintService complaintService, ComplaintMapperImpl complaintMapperImpl) {
        this.complaintService = complaintService;
        this.complaintMapperImpl = complaintMapperImpl;
    }

    @RequestMapping(path = "/showList")
    public ModelAndView showComplaintList(){
        ModelAndView mav = new ModelAndView("complaintList");
        mav.addObject("complaintList",complaintService.getAllComplaints());
        return mav;
    }

    @RequestMapping(path="/updateComplaint")
    public RedirectView updateComplaint(@RequestParam Long id, @ModelAttribute ComplaintDTO complaintDTO){

        complaintService.updateComplaintById(complaintDTO,id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/complaint/showList");

        return redirectView;
    }



    @RequestMapping(path = "/deleteComplaint")
    public RedirectView deleteComplaint(@RequestParam Long id){

        complaintService.deleteComplaintById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/complaint/showList");

        return redirectView;
    }
    }


