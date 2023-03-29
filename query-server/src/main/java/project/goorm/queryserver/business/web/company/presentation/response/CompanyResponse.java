package project.goorm.queryserver.business.web.company.presentation.response;

import project.goorm.queryserver.business.core.domain.company.entity.Company;
import project.goorm.queryserver.business.core.domain.company.entity.TopSearchedCompany;

import java.io.Serializable;

public class CompanyResponse implements Serializable {

    private String companyName;
    private int newsCount;
    private String mainImageUrl;

    private CompanyResponse(Company company) {
        this.companyName = company.getCompanyName();
        this.newsCount = company.getNewsCount();
        this.mainImageUrl = company.getMainImageUrl();
    }

    private CompanyResponse(TopSearchedCompany company) {
        this.companyName = company.getCompanyName();
        this.mainImageUrl = company.getMainImageUrl();
    }

    public static CompanyResponse of(Company company) {
        return new CompanyResponse(company);
    }

    public static CompanyResponse of(TopSearchedCompany company) {
        return new CompanyResponse(company);
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getNewsCount() {
        return newsCount;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    @Override
    public String toString() {
        return companyName;
    }
}
