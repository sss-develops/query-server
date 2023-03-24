package project.goorm.queryserver.business.web.company.presentation.response;

import project.goorm.queryserver.business.core.domain.company.entity.Company;

public class CompanyResponse {

    private String companyName;
    private int newsCount;
    private String mainImageUrl;

    public CompanyResponse(Company company) {
        this.companyName = company.getCompanyName();
        this.newsCount = company.getNewsCount();
        this.mainImageUrl = company.getMainImageUrl();
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
