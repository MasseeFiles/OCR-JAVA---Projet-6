package com.paymybuddy.repository;

import com.paymybuddy.model.MoneyTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyTransactionRepository extends CrudRepository<MoneyTransaction, Integer> { // Lien à l'entité MoneyTransaction
    Iterable<MoneyTransaction> findAllByGiverEmail(String giverEmail);
}

