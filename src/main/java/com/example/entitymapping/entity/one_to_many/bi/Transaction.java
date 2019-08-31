package com.example.entitymapping.entity.one_to_many.bi;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TRANSACTION_TBL")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_TRANS")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCT_ID")
    private Account account;

    @Column(name = "TRAN_TYPE")
    private String tranType;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "NOTES")
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
