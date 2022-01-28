package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.demo.service.PinTerminalRegistrationService;

@RestController
public class PinTerminalRegistrationController {

    @Autowired
    PinTerminalRegistrationService pinTerminalRegistrationService;

    @RequestMapping(value = "/registerPinTerminal", method = RequestMethod.GET)
    public @ResponseBody
    String registrationCodePinTerminal(@Param("accountIBAN") String accountIBAN) {
        return pinTerminalRegistrationService.registerPinTerminal(accountIBAN);
    }
}
