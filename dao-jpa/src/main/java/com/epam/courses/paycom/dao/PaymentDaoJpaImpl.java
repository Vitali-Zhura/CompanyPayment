package com.epam.courses.paycom.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.epam.courses.paycom.model.Payment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PaymentDaoJpaImpl implements PaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stream<Payment> findAll() {
        String sql = "Select b from Payment b";
        List<Payment> payments = entityManager.createQuery(sql, Payment.class).getResultList();
        return payments.stream();
    }

    @Override
    public Optional <Payment> findById(Integer companyId) {
        Payment payment = entityManager.find(Payment.class, companyId);
        return Optional.ofNullable(payment);
    }

    @Override
    public Stream <Payment> findByDate(Date beginDate, Date endDate) {
       LocalDate endDateLocal = endDate.toLocalDate().plusDays(1);
       endDate = endDate.valueOf(endDateLocal);

        String sql = "Select b from Payment b where b.paymentDate >= :beginDate AND b.paymentDate < :endDate";
        List<Payment> payments = entityManager.createQuery(sql, Payment.class)
                .setParameter("beginDate", beginDate)
                .setParameter( "endDate", endDate)
                .getResultList();
        return payments.stream();
    }

    @Override
    public Optional<Payment> add(Payment payment) {
        entityManager.persist(payment);
        return Optional.of(payment);
    }

    @Override
    public void cancel(int paymentId) {
        entityManager.remove(findById(paymentId).get());
    }
}
