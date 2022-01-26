package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.demo.dto.AccountDTO;
import project.demo.dto.ClientAuthorizationDTO;
import project.demo.dto.PaymentDataDTO;
import project.demo.service.BankAccountService;
import project.demo.service.TransactionService;

import java.util.List;


@RestController
public class BankAccountController implements WebMvcConfigurer {
    //region ATTRIBUTES
    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionService transactionService;
    //endregion

    //region METHODS - POST (body)
    @PostMapping(value = "/saveNewTransaction")
    @ResponseBody
    public void updateBalanceFromAccount(Long transactionAmount, String ibanSender,String ibanReceiver, String description  ) {
        PaymentDataDTO paymentDataDTO = new PaymentDataDTO(ibanSender,ibanReceiver,transactionAmount,description);
        transactionService.processPayment(paymentDataDTO);
    }
    //endregion

    //region METHODS - GET (param)
    @RequestMapping(value = "/getClientNameByUsername", method = RequestMethod.GET)
    public String getClientNameByUsername(@Param("username") String username) {
        return bankAccountService.getClientNameByUsername(username);
    }
    //endregion

    //region METHODS - GET (body)
    @RequestMapping(value = "/getAccountByIBAN", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AccountDTO> getAccountByAccountID(String iban) {
        AccountDTO foundAccountDTO= bankAccountService.getAccountByIban(iban);
        if (foundAccountDTO == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(foundAccountDTO);
    }

    @RequestMapping(value = "/getAccountWhereUserIsOwner", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<AccountDTO>> retrieveAccountWhereUserIsOwner(@RequestParam Long id) {
       List<AccountDTO> accountOwnedDTOS= bankAccountService.getListOfAccountDTOOwned(id);
       if (accountOwnedDTOS == null) return ResponseEntity.notFound().build();
       else return ResponseEntity.ok(accountOwnedDTOS);
    }

    @RequestMapping(value = "/getAccountWhereUserIsAuthorized", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<AccountDTO>> retrieveAccountWhereUserIsAuthorized(@RequestParam Long id) {
        List<AccountDTO> accountAuthDTOS = bankAccountService.getAllAuthorizedAccountDTOs(id);
        if (accountAuthDTOS == null) return ResponseEntity.notFound().build();
       else return ResponseEntity.ok(accountAuthDTOS);
    }

    @PostMapping("/authorizeThirdParty")
    public String authorizeThirdParty(@RequestBody ClientAuthorizationDTO clientAuthorizationDTO) {
        return bankAccountService.authorizeThirdParty(clientAuthorizationDTO);
    }

    @PutMapping("/acceptAuthorizationForAccount")
    public String acceptAuthorizationForAccount(@RequestBody ClientAuthorizationDTO clientAuthorizationDTO) {
        return bankAccountService.acceptAuthorizationForAccount(clientAuthorizationDTO);
    }

    @RequestMapping(value = "/checkIfIBANexists", method = RequestMethod.GET)
    public boolean checkIfIBANexists(@Param("iban") String iban) {
        return bankAccountService.checkIfIBANexists(iban);
    }

    @RequestMapping(value = "/checkIfSufficientFunds", method = RequestMethod.GET)
    public boolean checkIfSufficientFunds(@Param("funds") Long fundsInEuro, @Param("iban") String iban) {
        return bankAccountService.checkIfSufficientFunds(fundsInEuro, iban);
    }

    //endregion
}
