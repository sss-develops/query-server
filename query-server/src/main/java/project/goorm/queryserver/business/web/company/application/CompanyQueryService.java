package project.goorm.queryserver.business.web.company.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.queryserver.business.core.domain.company.entity.Company;
import project.goorm.queryserver.business.core.domain.company.infrastructure.query.CompanyQueryRepository;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.exception.common.SSSTeamException;

import java.util.List;
import java.util.stream.Collectors;

import static project.goorm.queryserver.business.core.domain.company.CompanyTypeException.COMPANY_IS_EMPTY_EXCEPTION;
import static project.goorm.queryserver.business.core.domain.company.CompanyTypeException.COMPANY_NOT_FOUND_EXCEPTION;

@Service
public class CompanyQueryService {

    private final CompanyQueryRepository companyQueryRepository;

    public CompanyQueryService(CompanyQueryRepository companyQueryRepository) {
        this.companyQueryRepository = companyQueryRepository;
    }

    @Transactional(readOnly = true)
    public CompanyResponse findCompanyById(Long companyId) {
        return new CompanyResponse(companyQueryRepository.findCompanyById(companyId)
                .orElseThrow(() -> SSSTeamException.of(COMPANY_NOT_FOUND_EXCEPTION)));
    }

    @Transactional(readOnly = true)
    public CompanyResponse findCompanyByName(String companyName) {
        return new CompanyResponse(companyQueryRepository.findCompanyByName(companyName)
                .orElseThrow(() -> SSSTeamException.of(COMPANY_NOT_FOUND_EXCEPTION)));
    }

    @Transactional(readOnly = true)
    public List<CompanyResponse> findCompanyAll() {
        List<Company> findCompany = companyQueryRepository.findCompanyAll()
                .orElseThrow(() -> SSSTeamException.of(COMPANY_IS_EMPTY_EXCEPTION));
        List<CompanyResponse> companyResponsesList = findCompany.stream()
                .map(c -> new CompanyResponse(c)).collect(Collectors.toList());
        return companyResponsesList;
    }
}
