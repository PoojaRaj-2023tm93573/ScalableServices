package transactionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transactionservice.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
