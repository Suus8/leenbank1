package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.dto.PaymentDataDTO;
import project.demo.dto.PaymentResultDTO;
import project.demo.dto.TransActionStatusCode;
import project.demo.dto.TransactionOverviewDTO;
import project.demo.model.Account;
import project.demo.model.Company;
import project.demo.model.Transaction;
import project.demo.repositories.IAccountRepo;
import project.demo.repositories.ITransactionRepo;
import project.demo.utility.ClientNameUtility;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    ITransactionRepo transactionRepo;
    @Autowired
    IAccountRepo accountRepo;

    //TODO: refactor below when general data location has been build;
    List<Account> allAccounts;

    PaymentDataDTO paymentData;
    int statuscode;
    Account accountSender;
    Account accountReceiver;

    public PaymentResultDTO processPayment(PaymentDataDTO paymentdata) {
        loadAccounts();
        setPaymentData(paymentdata);
        verifyDataPayment();

        long transactionId = 0;
        boolean dataCorrect = (statuscode == TransActionStatusCode.CODE_TRANSACTION_PROCESSED);
        if (dataCorrect) {
            Transaction t = new Transaction(accountSender, accountReceiver, paymentData.getAmountInCents(), paymentData.getDescription());
            processTransaction(t);
            t = transactionRepo.save(t);
            transactionId = t.getId();
        }
        PaymentResultDTO result = new PaymentResultDTO(dataCorrect, statuscode, transactionId);
        resetService();
        return result;
    }

    public List<TransactionOverviewDTO> getAllTransactionsForIban(String iban) {
        List<Transaction> allTransactions = (List<Transaction>) transactionRepo.findAll();
        List<TransactionOverviewDTO> transactionsForAccount = new ArrayList<>();
        for (Transaction t : allTransactions) {
            if (t.getAccountReciever().getAccountIBAN().equals(iban) || t.getAccountSender().getAccountIBAN().equals(iban))
                transactionsForAccount.add(makeDTOfromTransaction(t, iban));
        }
        transactionsForAccount.sort(new TransactionOverviewDTO.DateComparator());
        return transactionsForAccount;
    }

    private TransactionOverviewDTO makeDTOfromTransaction(Transaction t, String iban) {
        boolean receiver = (t.getAccountReciever().getAccountIBAN().equals(iban));
        String offsetAccountholder;
        String offsetIban;
        if (receiver) {
            offsetIban = t.getAccountSender().getAccountIBAN();
            offsetAccountholder = ClientNameUtility.getNameOfAbstractClientOfAccount(t.getAccountSender());
        } else {
            offsetIban = t.getAccountReciever().getAccountIBAN();
            offsetAccountholder = ClientNameUtility.getNameOfAbstractClientOfAccount(t.getAccountReciever());
        }
        return new TransactionOverviewDTO(offsetAccountholder, offsetIban, t.getDescription(), t.getDate(), t.getAmountInCents(), receiver, t.getId());
    }

    private void loadAccounts() {
        allAccounts = (List<Account>) accountRepo.findAll();
    }

    private void setPaymentData(PaymentDataDTO paymentDataDTO) {
        this.paymentData = paymentDataDTO;
        accountSender = giveAccountForIban(paymentDataDTO.getIbanSender());
        if (paymentDataDTO.getIdPaymentMachine() != 0) {
            accountReceiver = giveAccountForPinmachineId(paymentDataDTO.getIdPaymentMachine());
            if (accountReceiver != null)
                paymentDataDTO.addToDescription(ClientNameUtility.getNameOfAbstractClientOfAccount(accountReceiver));
        } else accountReceiver = giveAccountForIban(paymentDataDTO.getIbanReceiver());
    }

    private void verifyDataPayment() {
        if (paymentData.getPin() >= 0) {
            verifyPinCode();
            if (statuscode == TransActionStatusCode.CODE_PIN_NOT_CORRECT)
                return;
        }
        if (accountReceiver == null)
            statuscode = TransActionStatusCode.CODE_IBAN_RECEIVER_NOT_FOUND;
        else if (accountSender == null)
            statuscode = TransActionStatusCode.CODE_IBAN_SENDER_NOT_FOUND;
        else if (accountSender.getBalanceInCents() < paymentData.getAmountInCents())
            statuscode = TransActionStatusCode.CODE_BALANCE_NOT_SUFFICIENT;
        else
            statuscode = TransActionStatusCode.CODE_TRANSACTION_PROCESSED;
    }

    private void verifyPinCode() {
        if (accountSender != null) {
            if (accountSender.getPincode() != paymentData.getPin())
                statuscode = TransActionStatusCode.CODE_PIN_NOT_CORRECT;
        }
    }

    private void processTransaction(Transaction t) {
        accountSender.subtractFromBalance(t.getAmountInCents());
        accountSender.addTransactionToTransactionHistory(t);
        accountReceiver.addToBalance(t.getAmountInCents());
        accountReceiver.addTransactionToTransactionHistory(t);
    }

    private void resetService() {
        paymentData = null;
        accountReceiver = null;
        accountSender = null;
        allAccounts = null;
        statuscode = 0;
    }

    private Account giveAccountForIban(String iban) {
        for (Account a : allAccounts) {
            if (a.getAccountIBAN().equals(iban)) return a;
        }
        return null;
    }

    private Account giveAccountForPinmachineId(long idPaymentMachine) {
        for (Account a : allAccounts) {
            if (a.getAccountHolder() instanceof Company) {
                long companyPinId = ((Company) a.getAccountHolder()).getRegistrationCodePinTerminal();
                if (companyPinId == idPaymentMachine) return a;
            }
        }
        return null;
    }


}
