package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import project.demo.model.Company;
import project.demo.model.Person;
import project.demo.service.OpenAccountService;


@RestController
public class OpenAccountController {
    //region ATTRIBUTES
    @Autowired
    private OpenAccountService openAccountService;
    //endregion

    //region METHODS - POST (body)
    @PostMapping("/createNewConsumerAccount")
    public String createNewConsumerAccount(@RequestBody Person newClient) {
        openAccountService.createNewConsumerAccount(newClient);
        return "Welkom " + newClient.getPersonDetails().getFullName().getFullNameAsString() + ", uw account bij LeenmanBank is aangemaakt";
    }

    @PostMapping("/createNewSmallBusinessAccount")
    public String createNewSmallBusinessAccount(@RequestBody Company newCompany) {
        openAccountService.createNewSmallBusinessAccount(newCompany);
        return "Uw bedrijfsaccount bij LeenmanBank is aangemaakt. Bedankt voor uw vertrouwen in ons, " + newCompany.getCompanyDetails().getName() + "!";
    }
    //endregion

    //region METHODS - GET (param)
    @RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
    public boolean checkUsername(@Param("username") String username) {
        return openAccountService.checkUsername(username);
    }

    @RequestMapping(value = "/checkSsn", method = RequestMethod.GET)
    public boolean checkSsn(@Param("ssn") Long ssn) {
        return openAccountService.checkSsn(ssn);
    }

    @RequestMapping(value = "/checkCoC", method = RequestMethod.GET)
    public boolean checkCoC(@Param("coC") Long coC) {
        return openAccountService.checkCoC(coC);
    }
    //endregion

    //region METHODS - GET (body)

    //endregion

}
