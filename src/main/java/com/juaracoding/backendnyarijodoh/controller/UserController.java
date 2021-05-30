package com.juaracoding.backendnyarijodoh.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.juaracoding.backendnyarijodoh.entity.User;
import com.juaracoding.backendnyarijodoh.respository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/register/")
	public String addRegis (@RequestParam(value="file")MultipartFile images, @ModelAttribute(value="data") String dataJson) throws IOException {
		String fileName = StringUtils.cleanPath(images.getOriginalFilename());
		
		String uploadDir = "src/main/resources/static/user-photo/";
		com.juaracoding.backendnyarijodoh.utility.FileUtility.saveFile(uploadDir, fileName, images);
		User user = new Gson().fromJson(dataJson, User.class);
		
		user.setFoto(fileName);
		userRepo.save(user);
		return "Register Berhasil";
	}
	
	@GetMapping("/login/{username}/{nomor}")
	public User loginUser(@PathVariable String username, @PathVariable String nomor) {
		return userRepo.findByLogin(username, nomor);
	}
	
	
	@GetMapping("/getdata/{jeniskelamin}")
	public List<User> getUserJenisKelamin(@PathVariable String jeniskelamin){
		return (List<User>) userRepo.findAllByJenisKelamin(jeniskelamin);
	}
	
	@GetMapping("/{id}")
	public Optional<User> UserId(@PathVariable String id) {
		return userRepo.findById(Long.parseLong(id));
	}
	
	
}
	

