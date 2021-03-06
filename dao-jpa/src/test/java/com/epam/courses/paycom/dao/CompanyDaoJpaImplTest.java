package com.epam.courses.paycom.dao;

import com.epam.courses.paycom.stub.CompanyStub;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.epam.courses.paycom.model.Company;
import java.util.stream.Stream;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(true)
public class CompanyDaoJpaImplTest {

    @Autowired
    private CompanyDao companyDao;

    @Test
    public void findAll() {
        Stream<Company> companies = companyDao.findAll();
        assertNotNull(companies);
        assertEquals(companies.count(), 4);
    }

    @Test
    public void addCompany() {
        Stream<Company> companiesBeforeInsert = companyDao.findAll();
        Company company = new Company();
        company.setCompanyAccount("www");
        company.setCompanyName("qqq");
        companyDao.add(company);

        Stream<Company> companiesAfterInsert = companyDao.findAll();
        assertEquals(1, companiesAfterInsert.count() - companiesBeforeInsert.count());
    }

    @Test
    public void addDuplicateCompany() {
        Company company2 = new Company();
        company2.setCompanyAccount("aaa");
        company2.setCompanyName("bbb");
        companyDao.add(company2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            companyDao.add(company2);
        });
    }

    @Test
    public void findById() {
        Company company = companyDao.findById(1).get();
        assertNotNull(company);
        assertAll(
                () -> assertEquals(company.getCompanyId().intValue(), 1),
                () -> assertEquals(company.getCompanyAccount(), "BY27BLBB34325630287478004008"),
                () -> assertEquals(company.getCompanyName(), "Prestizh"));
    }

    @Test
    public void findByAccount() {
        Company company = companyDao.findByAccount("BY27BLBB34325630287478004008").get();
        assertNotNull(company);
        assertAll(
                () -> assertEquals(company.getCompanyId().intValue(), 1),
                () -> assertEquals(company.getCompanyAccount(), "BY27BLBB34325630287478004008"),
                () -> assertEquals(company.getCompanyName(), "Prestizh"));
    }

    @Test
    public void update() {
        Company company = new Company();
        company.setCompanyAccount("aaa");
        company.setCompanyName("bbb");
        Company newCompany = companyDao.add(company).get();
        assertNotNull(newCompany.getCompanyId());

        company.setCompanyAccount("aaa1");
        company.setCompanyName("bbb1");
        companyDao.update(company);

        Company updatedCompany = companyDao.findById(company.getCompanyId()).get();

        assertEquals("aaa1", updatedCompany.getCompanyAccount());
        assertEquals("bbb1", updatedCompany.getCompanyName());
    }

    @Test
    public void delete() {
        Stream<Company> companiesBeforeDelete = companyDao.findAll();
        Company company = companyDao.findById(3).get();
        companyDao.delete(company.getCompanyId());
        Stream<Company> companiesAfterDelete = companyDao.findAll();
        assertEquals(1, companiesBeforeDelete.count() - companiesAfterDelete.count());
    }

    @Test
    public void deleteUsingCompany() {
        Company company = companyDao.findById(2).get();
        Assertions.assertThrows(RuntimeException.class, () -> {
            companyDao.delete(company.getCompanyId());
        });
    }

    @Test
    public void findAllStubs() {
        Stream<CompanyStub> stub = companyDao.findAllStubs();
        assertNotNull(stub);
        assertTrue(stub.count() > 1);
    }
}
