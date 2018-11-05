package be.vdab.ethias.web;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import be.vdab.ethias.entities.Location;
import be.vdab.ethias.entities.Policy;
import be.vdab.ethias.entities.PolicyType;
import be.vdab.ethias.exceptions.CarClientTransportException;
import be.vdab.ethias.services.CarService;
import be.vdab.ethias.services.CustomerService;
import be.vdab.ethias.services.LocationService;
import be.vdab.ethias.valueobjects.Address;

@Controller
@RequestMapping(path ="/customers")
class CustomerController {
	private static final String CUSTOMERS_VIEW = "customers/customers";
	private static final String CUSTOMER_VIEW = "customers/customer";
	private static final String TOEGEVOEGD_VIEW = "customers/toegevoegd";
	private static final String REDIRECT_CUSTOMER_NOT_FOUND = "redirect:/";
	private final CustomerService customerService;
	private final CarService carService;
	private final LocationService locationService;
		
	CustomerController(CustomerService customerService, CarService carService, LocationService locationService) {
		this.customerService = customerService;
		this.carService = carService;
		this.locationService = locationService;
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
					try {
						BigDecimal soapCatalogPrice = carService.getCarResponse(((CarPolicy) policy).getBrand(), ((CarPolicy) policy).getModel()).getCar().getCatalogPrice();
						((CarPolicy) policy).setCatalogPrice(soapCatalogPrice);
						System.out.println("soap request calalogprice ok!!!");
					}catch(CarClientTransportException ex) {
						System.out.println(ex.getMessage());
					}
				}
			}
			return new ModelAndView(CUSTOMER_VIEW).addObject("customer", customer.get());
		}
		redirectAttributes.addAttribute("error", "Customer not found!");
		return new ModelAndView(REDIRECT_CUSTOMER_NOT_FOUND);
		
	}
	
	@RequestMapping(value = "/toevoegen", method = RequestMethod.POST)
	ModelAndView toevoegen() {
		//Location location = new Location(4L, (short) 3740, "BILZEN");
		//Address address = new Address("Teststreet", "999", location);
		//Customer customer = new Customer("TestFirstName", "TestSurname", 72092520736L, address, "testemail@hotmail.com");
		Optional<Customer> customer = customerService.findById(1L);
		if(customer.isPresent()) {
			boolean added = customer.get().addPolicy(new CarPolicy("efijeifjeif", new PolicyType(1L, "CAR"), LocalDate.now(), customer.get(), "Ferrari", "V8", BigDecimal.valueOf(150000)));
			System.out.println(added);
		}
				
	    return new ModelAndView(TOEGEVOEGD_VIEW);
	}
	
}
