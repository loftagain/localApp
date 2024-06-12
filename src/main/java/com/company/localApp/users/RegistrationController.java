package com.company.localApp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, @RequestParam String role, @RequestParam("image") MultipartFile image, Model model) {
        if (image != null && !image.isEmpty()) {
            if (image.getContentType().equals("image/jpeg") || image.getContentType().equals("image/png")) {
                if (image.getSize() <= 2 * 1024 * 1024) { // Check size <= 2MB
                    try {
                        String imagePath = saveImage(image);
                        user.setImagePath(imagePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    model.addAttribute("error", "File size should be less than 2MB.");
                    return "register";
                }
            } else {
                model.addAttribute("error", "Only JPG and PNG images are allowed.");
                return "register";
            }
        }
        userService.save(user, role);
        return "redirect:/login";
    }

    private String saveImage(MultipartFile image) throws IOException {
        String uploadDir = "user-photos/";
        String originalFilename = image.getOriginalFilename();
        String imagePath = uploadDir + UUID.randomUUID() + "_" + originalFilename;
        File uploadFile = new File(imagePath);
        uploadFile.getParentFile().mkdirs();
        Files.copy(image.getInputStream(), uploadFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return imagePath;
    }
}
