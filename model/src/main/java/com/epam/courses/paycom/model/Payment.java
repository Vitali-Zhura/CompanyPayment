package com.epam.courses.paycom.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name="Payment")
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId", updatable = false, nullable = false)
    private Integer paymentId;

    @Column(name = "payerName")
    private String payerName;

    @Column(name = "paymentSum")
    private Integer paymentSum;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "companyAccount", nullable = false, referencedColumnName = "companyAccount")
    private Company companyAccount;

    @Column(name = "paymentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public Integer getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(Integer paymentSum) {
        this.paymentSum = paymentSum;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Company getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(Company companyAccount) {
        this.companyAccount = companyAccount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", payerName='" + payerName + '\'' +
                ", paymentSum='" + paymentSum + '\'' +
                ", companyAccount='" + companyAccount + '\'' +
                ", paymentData='" + paymentDate + '\'' +
                '}';
    }

}
