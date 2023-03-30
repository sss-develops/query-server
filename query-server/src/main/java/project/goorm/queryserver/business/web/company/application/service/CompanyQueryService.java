package project.goorm.queryserver.business.web.company.application.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goorm.queryserver.business.core.domain.company.entity.Company;
import project.goorm.queryserver.business.core.domain.company.infrastructure.query.CompanyQueryRepository;
import project.goorm.queryserver.business.web.common.paging.Cursor;
import project.goorm.queryserver.business.web.company.application.CompanySearchQuery;
import project.goorm.queryserver.business.web.company.presentation.response.CompanyResponse;
import project.goorm.queryserver.common.exception.common.SSSTeamException;

import java.util.List;

import static project.goorm.queryserver.business.core.domain.company.CompanyTypeException.COMPANY_NOT_FOUND_EXCEPTION;
import static project.goorm.queryserver.common.configuration.redis.RedisKey.COMPANY_CACHE_CONDITION;
import static project.goorm.queryserver.common.configuration.redis.RedisKey.COMPANY_CACHE_KEY;
import static project.goorm.queryserver.common.configuration.redis.RedisKey.COMPANY_CACHE_VALUE;
import static project.goorm.queryserver.common.configuration.redis.RedisKey.TOP_SEARCHED_COMPANIES_CACHE_CONDITION;
import static project.goorm.queryserver.common.configuration.redis.RedisKey.TOP_SEARCHED_COMPANIES_CACHE_KEY;
import static project.goorm.queryserver.common.configuration.redis.RedisKey.TOP_SEARCHED_COMPANIES_CACHE_VALUE;

@Service
public class CompanyQueryService implements CompanySearchQuery {

    private final CompanyQueryRepository companyQueryRepository;
    private final RedisCacheService redisCacheService;

    public CompanyQueryService(
            CompanyQueryRepository companyQueryRepository,
            RedisCacheService redisCacheService
    ) {
        this.companyQueryRepository = companyQueryRepository;
        this.redisCacheService = redisCacheService;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            key = COMPANY_CACHE_KEY,
            value = COMPANY_CACHE_VALUE,
            condition = COMPANY_CACHE_CONDITION
    )
    public CompanyResponse searchCompanyId(Long companyId) {
        Company company = companyQueryRepository.searchCompanyId(companyId)
                .orElseThrow(() -> SSSTeamException.of(COMPANY_NOT_FOUND_EXCEPTION));
        return CompanyResponse.of(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> searchCompaniesName(
            Cursor cursor,
            String companyName
    ) {
        return companyQueryRepository.searchCompaniesName(
                cursor,
                companyName
                ).stream()
                .map(CompanyResponse::of)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            key = TOP_SEARCHED_COMPANIES_CACHE_KEY,
            value = TOP_SEARCHED_COMPANIES_CACHE_VALUE,
            condition = TOP_SEARCHED_COMPANIES_CACHE_CONDITION
    )
    public List<CompanyResponse> getTopSearchedCompanies() {

        return companyQueryRepository.findTopSearchedCompanies().stream()
                .map(CompanyResponse::of)
                .toList();
    }
}
