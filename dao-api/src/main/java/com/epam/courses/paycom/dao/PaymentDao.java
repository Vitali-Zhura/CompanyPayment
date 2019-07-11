package com.epam.courses.paycom.dao;

import com.epam.courses.paycom.model.Payment;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Stream;

public interface PaymentDao {

    Stream<Payment> findAll();

    Optional<Payment> findById(Integer paymentId);

    Stream<Payment> findByDate(Date beginDate, Date endDate);

    Optional<Payment> add(Payment payment);

    void cancel (int paymentId);

}
