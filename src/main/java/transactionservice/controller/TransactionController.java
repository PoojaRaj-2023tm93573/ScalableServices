package transactionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import transactionservice.model.Transaction;
import transactionservice.service.TransactionService;

import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestParam String status) {
        return transactionService.updateTransaction(id, status);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping("/validate/user")
    public String validateUser(@RequestHeader String  token) {
        return transactionService.validateUser(token);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        boolean isDeleted = transactionService.deleteTransaction(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();  // Status 204 - No Content
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Status 404 - Not Found
    }

}
