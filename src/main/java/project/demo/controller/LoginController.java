/**
 * Created by abuzer.alaca on 14/01/2022
 **/


package project.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.demo.dto.ClientDto;
import project.demo.model.Login;
import project.demo.service.LoginService;
import project.demo.utils.UserNotFountException;
import project.demo.utils.WrongPasswordException;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/login")
    public ResponseEntity<ClientDto> login(@RequestBody Login login) {
        ClientDto clientDto;
        try {
            clientDto = loginService.login(login);
        } catch (UserNotFountException e) {
            return ResponseEntity.notFound().build();
        } catch (WrongPasswordException e) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientDto);
    }

    @GetMapping("/login")
    public String login() {
        return "Hello World";
    }
}
