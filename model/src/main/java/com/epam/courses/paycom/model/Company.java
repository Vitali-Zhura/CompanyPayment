    package com.epam.courses.paycom.model;

    import javax.persistence.Column;
    import javax.persistence.Entity;
    import javax.persistence.Id;
    import javax.persistence.Table;

    @Entity
    @Table(name = "companies")
    public class Company {

        @Id
        @Column(name = "companyId")
        private Integer companyId;

        @Column(name = "companyAccount")
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
