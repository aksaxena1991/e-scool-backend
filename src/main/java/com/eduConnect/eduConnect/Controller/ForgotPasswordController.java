package com.eduConnect.eduConnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduConnect.eduConnect.Dto.ResponseMessageDto;
import com.eduConnect.eduConnect.Model.UsersManagement;
import com.eduConnect.eduConnect.Service.ForgotPasswordService;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseMessageDto> forgotPassword(@RequestBody UsersManagement request) {
        try {
            forgotPasswordService.sendOTP(request.getEmail());
            return ResponseEntity.ok(new ResponseMessageDto("success", "OTP sent successfully", null, new Date()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessageDto("error", "Failed to send OTP", e.getMessage(), new Date()));
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ResponseMessageDto> verifyOTP(@RequestBody UsersManagement request) {
        boolean verified = forgotPasswordService.verifyOTP(request.getEmail(), request.getOtp());
        if (verified) {
            return ResponseEntity.ok(new ResponseMessageDto("success", "OTP verified successfully", null, new Date()));
        } else {
            return ResponseEntity.ok(new ResponseMessageDto("error", "Invalid OTP", null, new Date()));
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<ResponseMessageDto> updatePassword(@RequestBody UsersManagement request) {
        try {
            forgotPasswordService.updatePassword(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new ResponseMessageDto("success", "Password updated successfully", null, new Date()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessageDto("error", "Failed to update password", e.getMessage(), new Date()));
        }
    }
}
