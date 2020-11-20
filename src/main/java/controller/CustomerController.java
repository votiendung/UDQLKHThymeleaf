package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    ICustomerService customerService;
    @GetMapping
    public ModelAndView showCustomerList() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("customerList",customerService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("customer", new Customer());
        return "/create";
    }
    @PostMapping("/save")
    public String save(Customer customer, RedirectAttributes redirectAttributes) {
        customer.setId((int) (Math.random()*10000));
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("success","Saved customer successfully!");
        return  "redirect:/customer";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/edit";
    }
    @PostMapping("/edit")
    public String edit(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.update(customer.getId(),customer);
        redirectAttributes.addFlashAttribute("success","Modified customer su okkkokko");
        return "redirect:/customer";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        customerService.remove(id);
        return "redirect:/customer";
    }



}
