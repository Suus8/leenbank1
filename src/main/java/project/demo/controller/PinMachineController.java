package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.demo.dto.PaymentDataDTO;
import project.demo.dto.PaymentResultDTO;
import project.demo.service.TransactionService;

@RestController
public class PinMachineController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/pinpayment")
    public @ResponseBody
    PaymentResultDTO pinPaymentRequest(@RequestBody PaymentDataDTO payment) {
        return transactionService.processPayment(payment);
    }
}
