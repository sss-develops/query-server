package project.goorm.queryserver.test.helper.fixture.company;

import project.goorm.queryserver.business.core.domain.common.deleted.Deleted;
import project.goorm.queryserver.business.core.domain.company.entity.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyListFixture {

    public static List<Company> createCompanyList() {
        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company("Acacia Research", 5, "Acacia Research.com", Deleted.FALSE));
        companyList.add(new Company("Acadia Hlthcre", 65, "Acadia Hlthcre.com", Deleted.FALSE));
        companyList.add(new Company("ACADIA Pharmaceuticals", 43, "ACADIA Pharmaceuticals.com", Deleted.FALSE));
        companyList.add(new Company("Accurayorped", 41, "Accurayorped.com", Deleted.FALSE));
        companyList.add(new Company("Acer Therapeutics", 456, "Acer Therapeutics.com", Deleted.FALSE));
        companyList.add(new Company("Aceragen", 56, "Aceragen.com", Deleted.FALSE));
        companyList.add(new Company("ACI Worldwide", 86, "ACI Worldwide.com", Deleted.FALSE));
        companyList.add(new Company("ACNB Corp", 39, "ACNB Corp.com", Deleted.FALSE));
        companyList.add(new Company("Acorda Therap", 50, "Acorda Therap.com", Deleted.FALSE));
        companyList.add(new Company("AcelRx Pharmaceuticals", 94, "AcelRx Pharmaceuticals.com", Deleted.FALSE));
        companyList.add(new Company("Achieve Life Sciences Inc", 321, "Achieve Life Sciences Inc.com", Deleted.FALSE));
        companyList.add(new Company("ACS", 53, "ACS.com", Deleted.FALSE));
        companyList.add(new Company("Ackermans & Van Haaren NV (ACKB)", 44, "Ackermans & Van Haaren NV (ACKB).com", Deleted.FALSE));
        companyList.add(new Company("ACB", 23, "ACB.com", Deleted.FALSE));
        companyList.add(new Company("ACC Binh Duong Investment and Construction JSC ", 10, "ACC Binh Duong Investment and Construction JSC.com", Deleted.FALSE));
        return companyList;
    }
}
