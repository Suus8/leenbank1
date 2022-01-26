package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.demo.dto.TransactionOverviewDTO;
import project.demo.service.TransactionService;

import java.util.List;

@Controller
public class TransactionOverviewController {

    @Autowired
    TransactionService service;

    @GetMapping("/transactionForIban")
    public @ResponseBody List<TransactionOverviewDTO> giveTransactionsForIban(@RequestParam String iban){
        return service.getAllTransactionsForIban(iban);
    }

}
