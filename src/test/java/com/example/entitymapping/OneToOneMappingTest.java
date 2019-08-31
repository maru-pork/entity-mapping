package com.example.entitymapping;

import com.example.entitymapping.repository.one_to_one.CredentialBiRepository;
import com.example.entitymapping.repository.one_to_one.CredentialUniRepository;
import com.example.entitymapping.repository.one_to_one.UserBiRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        properties = {
                "spring.datasource.url=jdbc:mysql://localhost:3306/one_to_one",
                "spring.datasource.username=root",
                "spring.datasource.password=",
                "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect",
                //"spring.jpa.properties.hibernate.format_sql=true",
                "spring.jpa.show-sql=true",
                "spring.jpa.hibernate.ddl-auto=none"
        }
)
@Sql(scripts = "classpath:/script/one-to-one.sql")
@Transactional
@Commit
public class OneToOneMappingTest {

    @Autowired
    private CredentialUniRepository credentialUniRepository;

    @Autowired
    private CredentialBiRepository credentialBiRepository;

    @Autowired
    private UserBiRepository userBiRepository;

    @Test
    public void oneToOneUnidirectional() {
        com.example.entitymapping.entity.one_to_one.uni.User
                user = new com.example.entitymapping.entity.one_to_one.uni.User();
        user.setFirstName("Maru");
        user.setLastName("Chan");
        user.setEmailAdd("em@il.com");

        com.example.entitymapping.entity.one_to_one.uni.Credential
                credential = new com.example.entitymapping.entity.one_to_one.uni.Credential();
        credential.setUsername("marukochan");
        credential.setPassword("p@ssword");

        credential.setUser(user);

        credentialUniRepository.save(credential);
        /*
        Hibernate: insert into user_tbl (email_add, first_name, last_name, id_user) values (?, ?, ?, ?)
        Hibernate: insert into credential_tbl (password, user_id, user_name, id_credential) values (?, ?, ?, ?)
         */

        com.example.entitymapping.entity.one_to_one.uni.Credential
                dbCredential = credentialUniRepository.getOne(credential.getId());
        Assert.assertEquals("Maru", dbCredential.getUser().getFirstName());
    }

    @Test
    public void oneToOneBidirectional() {
        com.example.entitymapping.entity.one_to_one.bi.User
                user = new com.example.entitymapping.entity.one_to_one.bi.User();
        user.setFirstName("Marumar");
        user.setLastName("Chan");
        user.setEmailAdd("em@il.com");

        com.example.entitymapping.entity.one_to_one.bi.Credential
                credential = new com.example.entitymapping.entity.one_to_one.bi.Credential();
        credential.setUsername("marumarukochan");
        credential.setPassword("p@ssword");

        // managing the both side of relationships
        credential.setUser(user);
        user.setCredential(credential);

        credentialBiRepository.save(credential);
        /*
        Hibernate: insert into user_tbl (email_add, first_name, last_name, id_user) values (?, ?, ?, ?)
        Hibernate: insert into credential_tbl (password, user_id, user_name, id_credential) values (?, ?, ?, ?)
         */

        com.example.entitymapping.entity.one_to_one.bi.User
                dbUser =  userBiRepository.getOne(credential.getUser().getId());
        Assert.assertEquals("Marumar", user.getFirstName());
    }
}
