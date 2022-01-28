package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.demo.dto.ConnectionTerminalDTO;
import project.demo.dto.EmployeeConnectionResultDTO;
import project.demo.dto.TerminalConnectionResultDTO;
import project.demo.service.EmployeeConnectionService;
import project.demo.service.TerminalConnectionService;

@RestController
public class ConnectionController {

    @Autowired
    TerminalConnectionService terminalConnectionService;
    @Autowired
    EmployeeConnectionService employeeConnectionService;


    // todo Make sure that the generated validationCode is temporarily available for the next hour
    // todo Extra: collect CompanyInformation when employee checks accountnumber

//
//    @RequestMapping(value = "/checkAccountNumberByEmployee", method = RequestMethod.GET)
//    public int generateValidationCode() {
//        return EmployeeConnectionService.generateFiveDigitValidationCode();
//    }


    //Post method
    @PostMapping(value = "/connectionRequest")
    public @ResponseBody
    TerminalConnectionResultDTO connectionRequest(@RequestBody ConnectionTerminalDTO connectionrequest) {
        return terminalConnectionService.processConnectionRequest(connectionrequest);
    }

    @PostMapping(value = "/employeeConnectionRequest")
    public @ResponseBody
    EmployeeConnectionResultDTO validationCodeAndAccountInfo(@RequestBody ConnectionTerminalDTO connectionrequest){
        return employeeConnectionService.processConnectionRequest(connectionrequest);
    }

}
