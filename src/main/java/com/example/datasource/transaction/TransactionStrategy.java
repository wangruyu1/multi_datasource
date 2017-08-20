package com.example.datasource.transaction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "masterTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public interface TransactionStrategy {

}
