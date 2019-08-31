package com.example.entitymapping.entity.many_to_many.uni;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT_TBL")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ACCT")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ACCOUNT",
        joinColumns = @JoinColumn(name = "ACCT_ID", referencedColumnName = "ID_ACCT"),
        inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID_USER"))
    private Set<User> users = new HashSet<>();

    @Column(name = "NAME")
    private String name;

    @Column(name = "CURR_BAL")
    private BigDecimal currentBalance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

