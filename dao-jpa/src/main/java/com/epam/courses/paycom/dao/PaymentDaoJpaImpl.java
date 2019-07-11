ackage com.epam.courses.paycom.dao;

import com.epam.courses.paycom.dao.CompanyDao;
import com.epam.courses.paycom.model.Company;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PaymentDaoImpl implements PaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stream<Company> findAll() {
        String sql = "Select a from Company a";
        List<Company> companies = entityManager.createQuery(sql, Company.class).getResultList();
        return companies.stream();
    }

    @Override
    public Optional <Company> findById(Integer companyId) {
        Company company = entityManager.find(Company.class, companyId);
        return Optional.ofNullable(company);
    }

    @Override
    public Optional <Company> findByAccount(String companyAccount) {
        String sql = "Select a from Company a Where a.companyAccount=:companyAccount";
        Company company = entityManager.createQuery(sql, Company.class).setParameter("companyAccount", companyAccount).getSingleResult();
        return Optional.ofNullable(company);
    }

    @Override
    public Optional<Company> add(Company company) {
        entityManager.persist(company);
        return Optional.of(company);
    }

    @Override
    public void update(Company company) {
        entityManager.merge(company);
    }

    @Override
    public void delete(int companyId) {
        entityManager.remove(findById(companyId).get());
    }
}
