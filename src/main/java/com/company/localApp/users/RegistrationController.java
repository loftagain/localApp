package com.company.localApp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, @RequestParam("image") MultipartFile image, Model model) {
        // Check for field validation errors
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Check if nickname is unique
        if (userService.existsByNickname(userDTO.getNickname())) {
            model.addAttribute("error", "Nickname already taken");
            return "register";
        }

        // Check if email is unique
        if (userService.existsByEmail(userDTO.getEmail())) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }

        // Check password strength
        if (!isPasswordStrong(userDTO.getPassword())) {
            model.addAttribute("error", "Password must be at least 8 characters long and include a mix of letters, numbers, and special characters");
            return "register";
        }

        if (image != null && !image.isEmpty()) {
            if (image.getContentType().equals("image/jpeg") || image.getContentType().equals("image/png")) {
                if (image.getSize() <= 2 * 1024 * 1024) { // Check size <= 2MB
                    try {
                        String imagePath = saveImage(image);
                        userDTO.setImagePath(imagePath);
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

        User user = userDTOMapper.toEntity(userDTO);
        userService.save(user);
        return "redirect:/";
    }

    private boolean isPasswordStrong(String password) {
        // Check if the password meets the strength requirements
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
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
