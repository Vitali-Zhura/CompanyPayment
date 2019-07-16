    package com.epam.courses.paycom.model;

    import com.epam.courses.paycom.stub.CompanyStub;

    import javax.persistence.*;
    import java.io.Serializable;

    @SqlResultSetMapping(
            name = "BookValueMapping",
            classes = @ConstructorResult(
                    targetClass = CompanyStub.class,
                    columns = {
                            @ColumnResult(name = "companyId", type = Integer.class),
                            @ColumnResult(name = "companyName", type = String.class),
                            @ColumnResult(name = "counts", type = Integer.class),
                            @ColumnResult(name = "amounts", type = Integer.class)}))

    @Entity(name="Company")
    @Table(name = "company", uniqueConstraints={@UniqueConstraint(columnNames={"companyAccount"})})
    public class Company implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "companyId", updatable = false, nullable = false)
        private Integer companyId;

        @Column(name = "companyAccount", unique = true)
        private String companyAccount;

        @Column(name = "companyName")
        private String companyName;

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public String getCompanyAccount() {
            return companyAccount;
        }

        public void setCompanyAccount(String companyAccount) {
            this.companyAccount = companyAccount;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }


        @Override
        public String toString() {
            return "Company{" +
                    "companyId=" + companyId +
                    ", companyAccount='" + companyAccount + '\'' +
                    ", companyName='" + companyName + '\'' +
                    '}';
        }
    }

