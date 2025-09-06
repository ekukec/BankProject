package com.emanuelkukec.mybank.web;

import com.emanuelkukec.mybank.model.Transaction;
import com.emanuelkukec.mybank.service.TransactionService;
import com.emanuelkukec.mybank.web.forms.TransactionForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WebsiteController {
    private TransactionService transactionService;

    private String bankSlogan;

    public WebsiteController(TransactionService transactionService, @Value("${bank.slogan}") String bankSlogan) {
        this.transactionService = transactionService;
        this.bankSlogan = bankSlogan;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("slogan", bankSlogan);
        return "index.html";
    }

    @GetMapping("/account/{userId}")
    public String getAccount(@PathVariable("userId") String userId, Model model){
        buildAccountPageModel(userId, model);
        model.addAttribute("transactionForm", new TransactionForm());
        return "account.html";
    }

    @PostMapping("/account/{userId}")
    public String createAccount(@PathVariable("userId") String userId, @ModelAttribute @Validated TransactionForm transactionForm, BindingResult bindingResult, Model model){
        buildAccountPageModel(userId, model);

        if(bindingResult.hasErrors()){
            return "account.html";
        }

        transactionService.create(transactionForm.getAmount(), transactionForm.getReference(), transactionForm.getReceivingUserId());
        return "redirect:/account/" + userId;
    }

    private void buildAccountPageModel(String userId, Model model) {
        List<Transaction> transactions = transactionService.findByUserId(userId);
        model.addAttribute("transactions", transactions);
    }
}
