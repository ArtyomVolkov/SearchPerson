package volkov.search_person.controller;

import javax.naming.NamingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import volkov.search_person.model.User;
import volkov.search_person.service.SearchPersonService;

/**
 * Processes a search requests
 */
@Controller
@RequestMapping("/person")
public class PersonController {

	/**
	 * User views info about person
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getPerson(@RequestParam("username") String name, Model model)
			throws NamingException {
		User user = new User();
		SearchPersonService searchPersonService = new SearchPersonService();
		user = searchPersonService.getUser(searchPersonService.findUidByFullName(name));
		model.addAttribute("userLdap", user);
		return "person";
	}
}
