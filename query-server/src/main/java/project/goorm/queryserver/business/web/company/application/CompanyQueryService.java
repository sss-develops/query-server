package project.goorm.queryserver.business.web.company.application;

import org.springframework.stereotype.Service;
import project.goorm.queryserver.business.core.company.infrastructure.query.CompanyQueryRepository;

@Service
public class CompanyQueryService {

    private final CompanyQueryRepository companyQueryRepository;

    public CompanyQueryService(CompanyQueryRepository companyQueryRepository) {
        this.companyQueryRepository = companyQueryRepository;
    }
}
