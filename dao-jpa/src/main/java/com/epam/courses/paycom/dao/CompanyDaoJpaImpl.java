package com.epam.courses.paycom.dao;

import com.epam.courses.paycom.model.Company;
import com.epam.courses.paycom.stub.CompanyStub;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class CompanyDaoJpaImpl implements CompanyDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoJpaImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stream<Company> findAll() {
        LOGGER.debug("findAll()");
        String sql = "Select a from Company a";
        List<Company> companies = entityManager.createQuery(sql, Company.class).getResultList();
        return companies.stream();
    }

    @Override
    public Optional<Company> findById(Integer companyId) {
        LOGGER.debug("findById({})", companyId);
        Company company = entityManager.find(Company.class, companyId);
        return Optional.ofNullable(company);
    }

    @Override
    public Optional<Company> findByAccount(String companyAccount) {
        LOGGER.debug("findByAccount({})", companyAccount);
        String sql = "Select a from Company a Where a.companyAccount=:companyAccount";
        Company company = entityManager.createQuery(sql, Company.class).setParameter("companyAccount", companyAccount).getSingleResult();

        return Optional.ofNullable(company);
    }

    @Override
    public Optional<Company> add(Company company) {
        LOGGER.debug("add({})", company);
        if (accountExists(company.getCompanyAccount())==false)
        entityManager.persist(company);
        else {
            throw new IllegalArgumentException("Company with the same name already exsists in DB.");
        }
        return Optional.of(company);
    }

    @Override
    public void update(Company company) {
        LOGGER.debug("update({})", company);
        entityManager.merge(company);
    }

    @Override
    public void delete(int companyId) {
        LOGGER.debug("delete({})", companyId);
        String sql = "Delete from Company a Where a.companyId=:companyId";
        int isSuccessful = entityManager.createQuery(sql).setParameter("companyId", companyId).executeUpdate();
        if (isSuccessful == 0) {
            new RuntimeException("Failed to delete company from DB");
        }
    }

    @Override
    public Stream<CompanyStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        String sql = "SELECT c.companyId, c.companyName, " +
                " count(p.companyAccount) AS counts, " +
                "sum(p.paymentSum) AS amounts " +
                "FROM Company c LEFT JOIN Payment p ON " +
                "(c.companyAccount = p.companyAccount) GROUP BY c.companyName " +
                "Order BY companyId";
        List<CompanyStub> companies = this.entityManager.createNativeQuery(sql, "BookValueMapping").getResultList();
        return companies.stream();
    }

    public boolean accountExists(String companyAccount) {
        String sql = "Select a from Company a Where a.companyAccount=:companyAccount";
        int count = entityManager.createQuery(sql, Company.class).setParameter("companyAccount", companyAccount).getResultList().size();
        return count > 0 ? true : false;
    }

}