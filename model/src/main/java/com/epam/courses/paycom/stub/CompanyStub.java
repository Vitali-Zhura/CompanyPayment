package com.epam.courses.paycom.stub;


public class CompanyStub {

    private Integer companyId;

    private String companyName;

    private Integer counts;

    private Integer amounts;

    public CompanyStub() {

    }

    public CompanyStub(Integer companyId, String companyName, Integer counts, Integer amounts) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.counts = counts;
        this.amounts = amounts;
    }

    public Integer getId() {
        return companyId;
    }

    public void setId(Integer id) {
        this.companyId = companyId;
    }

    public CompanyStub id(Integer id) {
        this.companyId = companyId;
        return this;
    }

    public String getCompany() {
        return companyName;
    }

    public void setCompany(String companyName) {
        this.companyName = companyName;
    }

    public CompanyStub companyName(String company) {
        this.companyName = companyName;
        return this;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public CompanyStub counts(Integer counts) {
        this.counts = counts;
        return this;
    }

    public Integer getAmounts() {
        return amounts;
    }

    public void setAmounts(Integer amounts) {
        this.amounts = amounts;
    }

    public CompanyStub amounts(Integer amounts) {
        this.amounts = amounts;
        return this;
    }

    @Override
    public String toString() {
        return "CompanyStub{" +
                "id=" + companyId +
                ", company='" + companyName + '\'' +
                ", count='" + counts + '\'' +
                ", amounts='" + amounts + '\'' +
                '}';
    }
}