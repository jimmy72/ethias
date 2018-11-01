package be.vdab.ethias.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.ethias.services.CustomerService;

@Controller
@RequestMapping(path ="/customers")
class CustomerController {
	private static final String CUSTOMERS_VIEW = "customers/customers";
	private final CustomerService customerService;
	
	CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	ModelAndView customerList() {
		return new ModelAndView(CUSTOMERS_VIEW, "customers", customerService.findAll());
	}
}
