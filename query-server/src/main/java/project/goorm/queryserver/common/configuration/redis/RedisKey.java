package project.goorm.queryserver.common.configuration.redis;

public interface RedisKey {

    String NEWS_CACHE_KEY = "#newsId";
    String NEWS_CACHE_VALUE = "news::cache";
    String NEWS_CACHE_CONDITION = "#newsId!=null";
}
