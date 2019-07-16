package com.epam.courses.paycom.service;

import com.epam.courses.paycom.dao.PaymentDao;
import com.epam.courses.paycom.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService{

private static final Logger LOGGER = LoggerFactory.getLogger (PaymentServiceImpl.class);

    private PaymentDao dao;

    public PaymentServiceImpl(PaymentDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Payment> findAll() {
        LOGGER.debug("Find all payments");
        return dao.findAll().collect(Collectors.toList());
    }

    @Override
    public void add(Payment payment) {
        dao.add(payment);
    }

    @Override
    public Payment findById(Integer id) {
        LOGGER.debug("findById({})", id);
        return dao.findById(id)
                .orElseThrow(() -> new RuntimeException("Failed to get payment from DB"));
    }

    @Override
    public List<Payment> findByDate(Date beginDate, Date endDate) {
        LOGGER.debug("findByDate({})", beginDate, endDate);
        return dao.findByDate(beginDate, endDate).collect(Collectors.toList());
    }

    @Override
    public void cancel (int id) {
        LOGGER.debug("delete({})", id);
        dao.cancel(id);
    }
}
