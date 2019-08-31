package com.example.entitymapping;


import com.example.entitymapping.entity.many_to_many.uni.Account;
import com.example.entitymapping.entity.many_to_many.uni.User;
import com.example.entitymapping.repository.many_to_many.AccountRepository;
import com.example.entitymapping.repository.many_to_many.UserRepository;
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
                "spring.datasource.url=jdbc:mysql://localhost:3306/many_to_many",
                "spring.datasource.username=root",
                "spring.datasource.password=",
                "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect",
                //"spring.jpa.properties.hibernate.format_sql=true",
                "spring.jpa.show-sql=true",
                "spring.jpa.hibernate.ddl-auto=none"
        }
)
@Sql(scripts = "classpath:/script/many-to-many.sql")
@Transactional
@Commit
public class ManyToManyMappingTest {

        @Autowired
        private AccountRepository accountRepository;

        @Autowired
        private UserRepository userRepository;

        @Test
        public void manyToManyUniTest(){
                User user1 = new User();
                user1.setFirstName("Mary");
                user1.setLastName("Chan");
                user1.setEmailAdd("em@il.com");

                User user2 = new User();
                user2.setFirstName("Felice");
                user2.setLastName("Chua");
                user2.setEmailAdd("em@il.com");

                Account accountMary = new Account();
                accountMary.setName("Mary Deposit Account");
                accountMary.setCurrentBalance(BigDecimal.TEN);
                accountMary.getUsers().add(user1);
                accountMary.getUsers().add(user2);

                Account accountRu = new Account();
                accountRu.setName("Ru Withdraw Account");
                accountRu.setCurrentBalance(BigDecimal.ONE);
                accountRu.getUsers().add(user1);

                accountRepository.save(accountMary);
                /*
                Hibernate: insert into account_tbl (curr_bal, name, id_acct) values (?, ?, ?)
                Hibernate: insert into user_tbl (email_add, first_name, last_name, id_user) values (?, ?, ?, ?)
                Hibernate: insert into user_tbl (email_add, first_name, last_name, id_user) values (?, ?, ?, ?)
                Hibernate: insert into user_account (acct_id, user_id) values (?, ?)
                Hibernate: insert into user_account (acct_id, user_id) values (?, ?)
                 */
                accountRepository.save(accountRu);

                Account dbAccountMary = accountRepository.getOne(accountMary.getId());
                Assert.assertEquals(2, dbAccountMary.getUsers().size());

                Account dbAccountRu = accountRepository.getOne(accountRu.getId());
                Assert.assertEquals(1, dbAccountRu.getUsers().size());
        }

        @Test
        public void manyToManyBiTest() {
                com.example.entitymapping.entity.many_to_many.bi.User
                        user1 = new com.example.entitymapping.entity.many_to_many.bi.User();
                        user1.setFirstName("Mar");
                        user1.setLastName("Roxas");
                        user1.setEmailAdd("em@il.com");

                com.example.entitymapping.entity.many_to_many.bi.User
                        user2 = new com.example.entitymapping.entity.many_to_many.bi.User();
                        user2.setFirstName("Mar");
                        user2.setLastName("Pangilinan");
                        user2.setEmailAdd("em@il.com");

                com.example.entitymapping.entity.many_to_many.bi.Account
                        accountSA = new com.example.entitymapping.entity.many_to_many.bi.Account();
                        accountSA.setName("Maru Savings Account");
                        accountSA.setCurrentBalance(BigDecimal.ONE);
                        accountSA.getUsers().add(user1);
                        accountSA.getUsers().add(user2);
                        user1.getAccounts().add(accountSA);
                        user2.getAccounts().add(accountSA);

                com.example.entitymapping.entity.many_to_many.bi.Account
                        accountTD= new com.example.entitymapping.entity.many_to_many.bi.Account();
                        accountTD.setName("Maru Time Deposit Account");
                        accountTD.setCurrentBalance(BigDecimal.ONE);
                        accountTD.getUsers().add(user1);
                        accountTD.getUsers().add(user2);
                        user1.getAccounts().add(accountTD);
                        user2.getAccounts().add(accountTD);

                userRepository.save(user1);
                /*
                Hibernate: insert into user_tbl (email_add, first_name, last_name, id_user) values (?, ?, ?, ?)
                Hibernate: insert into account_tbl (curr_bal, name, id_acct) values (?, ?, ?)
                Hibernate: insert into user_tbl (email_add, first_name, last_name, id_user) values (?, ?, ?, ?)
                Hibernate: insert into account_tbl (curr_bal, name, id_acct) values (?, ?, ?)
                Hibernate: insert into user_account (acct_id, user_id) values (?, ?)
                Hibernate: insert into user_account (acct_id, user_id) values (?, ?)
                Hibernate: insert into user_account (acct_id, user_id) values (?, ?)
                Hibernate: insert into user_account (acct_id, user_id) values (?, ?)
                 */
                userRepository.save(user2);

                com.example.entitymapping.entity.many_to_many.bi.User
                        dbUser1 = userRepository.getOne(user1.getId());
                        Assert.assertEquals(2, dbUser1.getAccounts().size());

                com.example.entitymapping.entity.many_to_many.bi.User
                        dbUser2 = userRepository.getOne(user1.getId());
                        Assert.assertEquals(2, dbUser2.getAccounts().size());
        }
}
