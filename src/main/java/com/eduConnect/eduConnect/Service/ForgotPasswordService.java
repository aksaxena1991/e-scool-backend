package com.eduConnect.eduConnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Model.UsersManagement;
import com.eduConnect.eduConnect.Repositroy.UsersManageRepositroy;

import java.util.Random;

@Service
public class ForgotPasswordService {

    @Autowired
    private UsersManageRepositroy userRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
	 private PasswordEncoder passwordEncoder;

    public void sendOTP(String email) {
        // Check if email exists in the database
    	UsersManagement user = userRepository.findByEmail(email);
        if (user == null) {
            // Email not found, handle accordingly (throw exception, return, etc.)
            // For example, you can throw an exception or return without sending OTP
            throw new RuntimeException("Email not found");
        }

        // Generate OTP
        String otp = generateOTP();

        // Save OTP to database
        user.setOtp(otp);
        userRepository.save(user);

        // Send OTP via email
        sendOTPEmail(email, otp);
    }

    public boolean verifyOTP(String email, String otp) {
    	UsersManagement user = userRepository.findByEmailAndOtp(email, otp);
        return user != null;
    }

    public void updatePassword(String email, String newPassword) {
    	UsersManagement user = userRepository.findByEmail(email);
        if (user != null) {
        	user.setPassword(passwordEncoder.encode(newPassword)); // Encrypt the password

//            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }

    private String generateOTP() {
        // Generate 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendOTPEmail(String email, String otp) {
        // Create email message
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Password Reset OTP");
        mailMessage.setText("Your OTP for password reset is: " + otp);

        // Send email
        javaMailSender.send(mailMessage);
    }
}
