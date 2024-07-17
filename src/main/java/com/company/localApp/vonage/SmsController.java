package com.company.localApp.vonage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private VonageService vonageService;

    @PostMapping("/send")
    public void sendSms(@RequestBody SmsRequest smsRequest) {
        vonageService.sendSms(smsRequest.getTo(), smsRequest.getMessage());
    }

    @Controller
    public static class WebController {

        @Autowired
        private VonageService vonageService;

        @GetMapping("/urgent")
        public String showUrgentSmsForm(Model model) {
            return "urgent";
        }

        @PostMapping("/sendUrgentSms")
        public String sendUrgentSms(@RequestParam("to") String to, @RequestParam("message") String message, Model model) {
            vonageService.sendSms(to, message);
            model.addAttribute("success", true);
            return "urgent";
        }
    }
}

class SmsRequest {
    private String to;
    private String message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}