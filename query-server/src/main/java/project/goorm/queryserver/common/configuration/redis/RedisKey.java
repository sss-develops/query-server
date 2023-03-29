package project.goorm.queryserver.common.configuration.redis;

public interface RedisKey {

    String NEWS_CACHE_KEY = "#newsId";
    String NEWS_CACHE_VALUE = "news::cache";
    String NEWS_CACHE_CONDITION = "#newsId!=null";

    String COMPANY_CACHE_KEY = "#companyId";
    String COMPANY_CACHE_VALUE = "company::cache";
    String COMPANY_CACHE_CONDITION = "#companyId!=null";

    String TOP_SEARCHED_COMPANIES_CACHE_KEY = "#topSearchedCompanies";
    String TOP_SEARCHED_COMPANIES_CACHE_VALUE = "topSearchedCompanies::cache";
    String TOP_SEARCHED_COMPANIES_CACHE_CONDITION = "#topSearchedCompanies!=null";
}
