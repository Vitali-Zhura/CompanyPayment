package com.epam.courses.paycom.dao;

import com.epam.courses.paycom.model.Company;
import com.epam.courses.paycom.model.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.stream.Stream;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(true)
public class PaymentDaoJpaImplTest {

    @Autowired
    private PaymentDao paymentDao;

    @Test
    public void findAll() {
        Stream<Payment> payments = paymentDao.findAll();
        assertNotNull(payments);
        assertEquals(payments.count(), 5);
    }

    @Test
    public void findById() {

        Payment payment = paymentDao.findById(1).get();
        assertNotNull(payment);
        assertAll(
                () -> assertEquals(payment.getPaymentId().intValue(), 1),
                () -> assertEquals(payment.getCompanyAccount().toString(), createCompany().toString()),
                () -> assertEquals(payment.getPayerName(), "Ivanov"),
                () -> assertEquals(payment.getPaymentSum().intValue(), 230),
                () -> assertEquals(payment.getPaymentDate().toString(), "2019-03-10 12:12:30.0"));
    }

    @Test
    public void addCompany()
    {
        Stream<Payment> paymentsBeforeInsert = paymentDao.findAll();
        Payment payment = new Payment();
        payment.setCompanyAccount(createCompany());
        payment.setPayerName("aaa");
        payment.setPaymentSum(100);
        paymentDao.add(payment);

        Stream<Payment> paymentsAfterInsert = paymentDao.findAll();
        assertEquals(1, paymentsAfterInsert.count() - paymentsBeforeInsert.count());
    }

    @Test
    public void findByDate() {

        Date beginDate = java.sql.Date.valueOf("2019-03-10");
        Date endDate = java.sql.Date.valueOf ("2019-03-10");

        Stream<Payment> payments= paymentDao.findByDate(beginDate, endDate);
        assertNotNull(payments);
        assertEquals(payments.count(), 3);
    }

    @Test
    public void cancel() {

        Stream<Payment> paymentsBeforeDelete = paymentDao.findAll();
        Payment payment =  paymentDao.findById(1).get();
        paymentDao.cancel(payment.getPaymentId());
        Stream<Payment> paymentsAfterDelete = paymentDao.findAll();
        assertEquals(1, paymentsBeforeDelete.count() - paymentsAfterDelete.count());
    }

    Company createCompany() {

        Company company = new Company();
        company.setCompanyId(1);
        company.setCompanyAccount("BY27BLBB34325630287478004008");
        company.setCompanyName("Prestizh");
        return company;
    }
}
