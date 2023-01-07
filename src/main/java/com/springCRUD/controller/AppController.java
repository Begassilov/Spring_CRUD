package com.springCRUD.controller;

import java.util.List;

import com.springCRUD.model.User;
import com.springCRUD.repository.RoleRepository;
import com.springCRUD.repository.UserRepository;
import com.springCRUD.services.BookServices;
import com.springCRUD.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.springCRUD.model.Book;

@Controller
public class AppController {

	@Autowired
    BookServices service;
	@Autowired
	UserRepository userRepository; //DELETE
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserServices userServices;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Book> listBook = service.listAll();
		model.addAttribute("listBook",listBook);
		return "index";
	}

	@RequestMapping("/new")
	public String newBookPage(Model model) {
		Book book=new Book();
		model.addAttribute(book);
		return "new_book";
	}

	@RequestMapping(value = "/save", method =RequestMethod.POST)
	public String saveBook(@ModelAttribute("book") Book book ) {
		service.save(book);
		return "redirect:/";
	}
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditBookPage(@PathVariable (name="id") Long id) {
		ModelAndView mav=new ModelAndView("edit_book");
		Book book=service.get(id);
		mav.addObject("book",book);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteBookPage(@PathVariable (name="id") Long id) {
		service.delete(id);
		return "redirect:/";
	}
	//search
	@PostMapping("/search")
	public String doSearchEmployee(@ModelAttribute("bookSearchFormData") Book formData, Model model) {
		Book book = service.get(formData.getId());
		model.addAttribute("book", book);
		System.out.println(book);
		return "search_results";
	}

	@RequestMapping ("/search")
	public String SearchEmployee() {
		return "search";
	}
	//user
	@RequestMapping("/user-list")
	public String view(Model model) {
		List<User> listUser = userServices.listAllUser();
		model.addAttribute("listUser",listUser);
		return "index";
	}

	@GetMapping("/user/save")
	public String AddUserForm(User user) {
		return "new_user";
	}

	@RequestMapping("/user/save")
	public String addUser(Model model, User user) {
		String pass = bCryptPasswordEncoder.encode(user.getPassword());
		model.addAttribute(user);
		user.setPassword(pass);
		user.setEnabled(true);
		user.getRoles().add(roleRepository.findRoleById(1));
		System.out.println(user.getRoles());
		userServices.save(user);
		return "redirect:/";
	}

	@GetMapping("user-delete/{id}")
	public String deleteUser(@PathVariable("id") Long id){
		userServices.delete(id);
		return "redirect:/users";
	}

	@GetMapping("user-update/{id}")
	public String updateUserForm(@PathVariable("id") Long id, Model model){
		User user = userServices.get(id);
		model.addAttribute("user", user);
		return "/edit_user";
	}

	@PostMapping("/user-update")
	public String updateUser(User user){
		userServices.save(user);
		return "redirect:/index";
	}
	//end user
}

