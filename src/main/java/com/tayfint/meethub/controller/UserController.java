package com.tayfint.meethub.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tayfint.meethub.dao.RoleDao;
import com.tayfint.meethub.model.User;
import com.tayfint.meethub.model.UserRole;
import com.tayfint.meethub.service.UserService;
import com.tayfint.meethub.validator.UserFormValidator;

@Controller
public class UserController {

	static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	UserFormValidator userFormValidator;

	@Autowired
	private RoleDao roleDao;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	// list page
	@RequestMapping(value = "/Users", method = RequestMethod.GET)
	public String showAllUsers(Model model) {

		logger.debug("showAllUsers()");
		model.addAttribute("users", userService.findUserList());
		return "users/list";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("user") @Validated User user, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {

		if (result.hasErrors()) {
			logger.info("Binding Errors : {}", result.getAllErrors().get(0));
			// populateDefaultModel(model);
			return "/index";
		} else if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
			if (userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}

			if (userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists", true);
			}
			return "/index";
		} else {

			// Add message to flash scope
			/*
			 * redirectAttributes.addFlashAttribute("css", "success"); if(user.isNew()){
			 * redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			 * }else{ redirectAttributes.addFlashAttribute("msg",
			 * "User updated successfully!"); }
			 */
			String pwd = user.getPassword();
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
			userService.createUser(user, userRoles);
			authWithHttpServletRequest(request, user.getUsername(), pwd);

			return "redirect:/home";

		}

	}
	
	public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            logger.error("Error while login ", e);
        }
    }

}
