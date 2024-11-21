package transactionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transactionservice.model.Transaction;
import transactionservice.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    ExternalServiceCaller externalServiceCaller;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, String status) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null) {
            transaction.setStatus(status);
            return transactionRepository.save(transaction);
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }
    public String validateUser(String  token) {
        return externalServiceCaller.callOtherServiceWithPost(token);
    }
}
