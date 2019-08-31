package com.example.entitymapping;

import com.example.entitymapping.entity.one_to_many.join.Budget;
import com.example.entitymapping.entity.one_to_many.uni.Account;
import com.example.entitymapping.entity.one_to_many.uni.Transaction;
import com.example.entitymapping.repository.one_to_many.AccountBiRepository;
import com.example.entitymapping.repository.one_to_many.AccountUniRepository;
import com.example.entitymapping.repository.one_to_many.BudgetJoinRepository;
import com.example.entitymapping.repository.one_to_many.TransactionBiRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        properties = {
                "spring.datasource.url=jdbc:mysql://localhost:3306/one_to_many",
                "spring.datasource.username=root",
                "spring.datasource.password=",
                "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect",
                //"spring.jpa.properties.hibernate.format_sql=true",
                "spring.jpa.show-sql=true",
                "spring.jpa.hibernate.ddl-auto=none"
        }
)
@Sql(scripts = "classpath:/script/one-to-many.sql")
@Transactional
@Commit
public class OneToManyMappingTest {

    @Autowired
    private AccountUniRepository accountUniRepository;

    @Autowired
    private AccountBiRepository accountBiRepository;

    @Autowired
    private TransactionBiRepository transactionBiRepository;

    @Autowired
    private BudgetJoinRepository budgetJoinRepository;

    private Transaction createUniTransaction(String type, BigDecimal amount, String notes) {
        Transaction transaction = new Transaction();
        transaction.setTranType(type);
        transaction.setAmount(amount);
        transaction.setNotes(notes);
        return transaction;
    }

    @Test
    public void oneToManyUnidirectional(){
        Account account = new Account();
        account.setName("Travel Fund");
        account.setCurrentBalance(BigDecimal.ONE);

        account.getTransactions().add(createUniTransaction("Deposit", BigDecimal.valueOf(100), "siargao travel"));
        account.getTransactions().add(createUniTransaction("Deposit", BigDecimal.valueOf(200), "baguio travel"));

        accountUniRepository.save(account);
        /**
         * Hibernate: insert into account_tbl (curr_balance, name, id_acct) values (?, ?, ?)
         * Hibernate: insert into transaction_tbl (amount, notes, tran_type, id_trans) values (?, ?, ?, ?)
         * Hibernate: insert into transaction_tbl (amount, notes, tran_type, id_trans) values (?, ?, ?, ?)
         * Hibernate: update transaction_tbl set acct_id=? where id_trans=?
         * Hibernate: update transaction_tbl set acct_id=? where id_trans=?
         */
        Account dbAccount = accountUniRepository.getOne(account.getId());
        Assert.assertEquals("siargao travel",  dbAccount.getTransactions().get(0).getNotes());
        Assert.assertEquals("baguio travel",  dbAccount.getTransactions().get(1).getNotes());
    }

    private com.example.entitymapping.entity.one_to_many.bi.Transaction createBiTransaction(
            com.example.entitymapping.entity.one_to_many.bi.Account account,
            String type, BigDecimal amount, String notes) {
        com.example.entitymapping.entity.one_to_many.bi.Transaction
                transaction = new com.example.entitymapping.entity.one_to_many.bi.Transaction();
        transaction.setAccount(account);
        transaction.setTranType(type);
        transaction.setAmount(amount);
        transaction.setNotes(notes);
        return transaction;
    }

    @Test
    public void oneToManyBidirectional() {
        com.example.entitymapping.entity.one_to_many.bi.Account
                account = new com.example.entitymapping.entity.one_to_many.bi.Account();
        account.setName("Movie Fund");
        account.setCurrentBalance(BigDecimal.ONE);

        account.getTransactions().add(
                createBiTransaction(account, "Deposit", BigDecimal.valueOf(1000), "cinemalaya"));
        account.getTransactions().add(
                createBiTransaction(account, "Deposit", BigDecimal.valueOf(2000), "cinemaone originals"));

        accountBiRepository.save(account);
        /*
        Hibernate: insert into account_tbl (curr_bal, name, id_acct) values (?, ?, ?)
        Hibernate: insert into transaction_tbl (account_id, amount, notes, tran_type, id_trans) values (?, ?, ?, ?, ?)
        Hibernate: insert into transaction_tbl (account_id, amount, notes, tran_type, id_trans) values (?, ?, ?, ?, ?)
        Hibernate: update transaction_tbl set acct_id=? where id_trans=?
        Hibernate: update transaction_tbl set acct_id=? where id_trans=?
         */

        com.example.entitymapping.entity.one_to_many.bi.Transaction
                dbTransaction = transactionBiRepository.getOne(account.getTransactions().get(0).getId());
        Assert.assertEquals("Movie Fund", dbTransaction.getAccount().getName());
    }

    private com.example.entitymapping.entity.one_to_many.join.Transaction createJoinTransaction(
            com.example.entitymapping.entity.one_to_many.join.Account account,
            String type, BigDecimal amount, String notes) {
        com.example.entitymapping.entity.one_to_many.join.Transaction
                transaction = new com.example.entitymapping.entity.one_to_many.join.Transaction();
        transaction.setAccount(account);
        transaction.setTranType(type);
        transaction.setAmount(amount);
        transaction.setNotes(notes);
        return transaction;
    }

    @Test
    public void joinTable() {
        com.example.entitymapping.entity.one_to_many.join.Account
                account = new com.example.entitymapping.entity.one_to_many.join.Account();
        account.setName("Personal Account");
        account.setCurrentBalance(BigDecimal.ONE);

        Budget budget = new Budget();
        budget.setName("Daily Budget");
        budget.setAmount(BigDecimal.valueOf(200));

        budget.getTransactions().add(
                createJoinTransaction(account, "Expense", BigDecimal.valueOf(16), "Fare"));
        budget.getTransactions().add(
                createJoinTransaction(account, "Expense", BigDecimal.valueOf(55), "Lunch"));

        budgetJoinRepository.save(budget);
		/*
		Hibernate: insert into budget_tbl (amount, name, id_budget) values (?, ?, ?)
		Hibernate: insert into account_tbl (curr_bal, name, id_acct) values (?, ?, ?)
		Hibernate: insert into transaction_tbl (account_id, amount, notes, tran_type, id_trans) values (?, ?, ?, ?, ?)
		Hibernate: insert into transaction_tbl (account_id, amount, notes, tran_type, id_trans) values (?, ?, ?, ?, ?)
		Hibernate: insert into budget_transaction (budget_id, trans_id) values (?, ?)
		Hibernate: insert into budget_transaction (budget_id, trans_id) values (?, ?)
		 */

        Budget dbBudget = budgetJoinRepository.getOne(budget.getId());
        Assert.assertEquals("Personal Account", dbBudget.getTransactions().get(0).getAccount().getName());
        Assert.assertEquals("Fare", dbBudget.getTransactions().get(0).getNotes());
        Assert.assertEquals("Lunch", dbBudget.getTransactions().get(1).getNotes());
    }
}
