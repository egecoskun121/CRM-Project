package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.ComplaintDTO;
import egecoskun121.com.crm.model.entity.Complaint;
import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.model.mapper.ComplaintMapperImpl;
import egecoskun121.com.crm.services.ComplaintService;
import egecoskun121.com.crm.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping("api/v1/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final ComplaintMapperImpl complaintMapperImpl;

    private final ProductService productService;

    public ComplaintController(ComplaintService complaintService, ComplaintMapperImpl complaintMapperImpl, ProductService productService) {
        this.complaintService = complaintService;
        this.complaintMapperImpl = complaintMapperImpl;
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/showList")
    public ModelAndView showComplaintList() {
        ModelAndView mav = new ModelAndView("complaint-list");
        mav.addObject("complaintList", complaintService.getAllComplaints());
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/showComplaintsByUsername")
    public ModelAndView showComplaintListByUsername(@RequestParam String username) {
        ModelAndView modelAndView = new ModelAndView("complaint-list-by-username");
        modelAndView.addObject("complaintList", complaintService.getAllComplaintsByUsername(username));
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/addNewComplaint")
    public ModelAndView addNewComplaint(@ModelAttribute ComplaintDTO complaintDTO, @RequestParam String username) {
        ModelAndView mav = new ModelAndView("raise-complaint");
        Complaint complaint = new Complaint();
        List<Product> productList = productService.getAllProductsByUsername(username);
        mav.addObject("complaint", complaint);
        mav.addObject("productList", productList);
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/saveNewComplaint/{username}")
    public RedirectView saveNewComplaint(@ModelAttribute ComplaintDTO complaintDTO, @PathVariable("username") String username) {
        complaintService.saveNewComplaint(complaintDTO, username);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/complaint/showComplaintsByUsername?username=" + username);
        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("update-complaint-form");
        Complaint complaint = complaintService.getComplaintById(id);
        mav.addObject("complaint", complaint);
        return mav;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/updateComplaint/{id}")
    public RedirectView updateComplaint(@PathVariable("id") Long id, @ModelAttribute ComplaintDTO complaintDTO) {

        complaintService.updateComplaintById(complaintDTO, id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/complaint/showList");
        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/updateComplaintByUsername/{id}/{username}")
    public RedirectView updateComplaintByUsername(@PathVariable("id") Long id, @PathVariable("username") String username, @ModelAttribute ComplaintDTO complaintDTO) {

        complaintService.updateComplaintById(complaintDTO, id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/complaint/showComplaintsByUsername?username=" + username);
        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/deleteComplaint")
    public RedirectView deleteComplaint(@RequestParam Long id) {

        complaintService.deleteComplaintById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/complaint/showList");

        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/deleteComplaintByUsername")
    public RedirectView deleteComplaintByUsername(@RequestParam Long id, @RequestParam String username) {

        complaintService.deleteComplaintById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/complaint/showComplaintsByUsername?username=" + username);

        return redirectView;
    }


}


