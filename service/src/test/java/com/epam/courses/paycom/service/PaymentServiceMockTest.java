package com.epam.courses.paycom.service;

import com.epam.courses.paycom.dao.PaymentDao;
import com.epam.courses.paycom.model.Company;
import com.epam.courses.paycom.model.Payment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;

public class PaymentServiceMockTest {

    private PaymentDao dao;

    private PaymentService service;

    private static final int ONE = 1;

    private static Payment  FIRST_PAYMENT;


    @BeforeAll
    static void init() {
        FIRST_PAYMENT = create(ONE);
    }

    @BeforeEach
    void setup() {
        dao = Mockito.mock(PaymentDao.class);
        service = new PaymentServiceImpl(dao);
    }

    @Test
    public void findAll() {

        Mockito.when(dao.findAll()).thenReturn(Stream.of(FIRST_PAYMENT));

        List<Payment> result = service.findAll();
        assertNotNull(result);
        assertEquals(ONE, result.size());

        Mockito.verify(dao, Mockito.times(ONE)).findAll();
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    public void findById() {

        Mockito.when(dao.findById(anyInt())).thenReturn(Optional.of(FIRST_PAYMENT));

        Payment result = service.findById(ONE);
        assertNotNull(result);
        assertEquals(result.getPaymentId(), FIRST_PAYMENT.getPaymentId());
        Mockito.verify(dao, Mockito.times(ONE)).findById(anyInt());
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    public void findByDate() {

        Mockito.when(dao.findByDate(any(), any())).thenReturn(Stream.of(FIRST_PAYMENT));
        List <Payment> result = service.findByDate(any(), any());
        assertNotNull(result);
        Mockito.verify(dao, Mockito.times(ONE)).findByDate(any(), any());
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    public void add() {

        Mockito.when(dao.add(any())).thenReturn(Optional.of(FIRST_PAYMENT));
        service.add(any());
        Mockito.verify(dao, Mockito.times(ONE)).add(any());
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    public void cancel() {

        dao.cancel(anyInt());
        Mockito.verify(dao, Mockito.times(ONE)).cancel(anyInt());
        Mockito.verifyNoMoreInteractions(dao);
    }

    private static Payment create(int index) {
        Payment payment = new Payment();
        payment.setPaymentId(index);
        payment.setPayerName("name" + index);
        payment.setPaymentSum(100 + index);
        payment.setCompanyAccount(createCompany(1));
        payment.setPaymentDate(java.sql.Timestamp.valueOf("2019-03-10 12:12:30"));
        return payment;
    }

    private static Company createCompany(int index) {
        Company company = new Company();
        company.setCompanyId(index);
        company.setCompanyAccount("account" + index);
        company.setCompanyName("name" + index);
        return company;
    }
}
