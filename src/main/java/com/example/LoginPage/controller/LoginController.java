package com.example.LoginPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LoginPage.services.PasswordRecovery;
import com.example.LoginPage.services.SendEmail;

@Controller
public class LoginController {

    private final SendEmail sendEmailService;
    private final PasswordRecovery passwordRecoveryService;

    public LoginController(SendEmail sendEmailService, PasswordRecovery passwordRecoveryService){
        this.sendEmailService = sendEmailService;
        this.passwordRecoveryService = passwordRecoveryService;
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }
    @GetMapping("/register")
    public String register() {
        return "Register";
    }
    @GetMapping("/recoverpassword")
    public String recoverpassword() {
        return "recoverpassword";
    }
    @PostMapping("/recoverpassword")
    public String handleRecoverPassword(
        @RequestParam("email") String email) throws Exception {

        String token = passwordRecoveryService.generateToken(email);

        String link = "http://localhost:8080/resetpassword?token="
                + token;

        sendEmailService.sendEmail(
                email,
                "Recuperação de Senha - PUC Minas",

                "Olá, Usuário!\n\n"
                        + "Recebemos uma solicitação para "
                        + "redefinir sua senha.\n\n"

                        + "Clique no link abaixo para criar "
                        + "uma nova senha:\n\n"

                        + link + "\n\n"

                        + "Este link é válido por 15 minutos.\n\n"

                        + "Se você não solicitou a recuperação "
                        + "da senha, ignore este e-mail.");

        System.out.println("Link de recuperação enviado para: " + email);

        return "redirect:/recoverpassword?sucesso=email";
    }

}
