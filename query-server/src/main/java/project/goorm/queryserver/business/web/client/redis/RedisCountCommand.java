package project.goorm.queryserver.business.web.client.redis;

public interface RedisCountCommand {

    void increaseCompanyIdCount(Long companyId);

}
