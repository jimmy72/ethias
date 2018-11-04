package be.vdab.ethias.web;

import java.util.Optional;
import java.util.Set;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.ethias.entities.CarPolicy;
import be.vdab.ethias.entities.Customer;
import be.vdab.ethias.entities.Policy;
import be.vdab.ethias.services.CarService;
import be.vdab.ethias.services.CustomerService;

@Controller
@RequestMapping(path ="/customers")
class CustomerController {
	private static final String CUSTOMERS_VIEW = "customers/customers";
	private static final String CUSTOMER_VIEW = "customers/customer";
	private static final String REDIRECT_CUSTOMER_NOT_FOUND = "redirect:/";
	private final CustomerService customerService;
	private final CarService carService;
		
	CustomerController(CustomerService customerService, CarService carService) {
		this.customerService = customerService;
		this.carService = carService;
	}
	
	@GetMapping
	ModelAndView customerList(HttpServletRequest request) {
		
		return new ModelAndView(CUSTOMERS_VIEW, "customers", customerService.findAll());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ModelAndView customerDetail(@PathVariable(name = "id") long customerId, RedirectAttributes redirectAttributes) {
		Optional<Customer> customer = this.customerService.findById(customerId);
		if (customer.isPresent()) {
			Set<Policy> policies = customer.get().getPolicies();
			for(Policy policy : policies) {
				if(policy instanceof CarPolicy) {
					((CarPolicy) policy).setCatalogPrice(carService.getCarResponse(((CarPolicy) policy).getBrand(), 
							((CarPolicy) policy).getModel()).getCar().getCatalogPrice());
				}
			}
			return new ModelAndView(CUSTOMER_VIEW).addObject("customer", customer.get());
		}
		redirectAttributes.addAttribute("error", "Customer not found!");
		return new ModelAndView(REDIRECT_CUSTOMER_NOT_FOUND);
		
	}
}
