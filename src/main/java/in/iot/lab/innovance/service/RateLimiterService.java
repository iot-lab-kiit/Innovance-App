package in.iot.lab.innovance.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class RateLimiterService {
    
    private final ReactiveStringRedisTemplate redisTemplate;
    private final int maxRequests;
    private final Duration timeWindow;
    
    public RateLimiterService(ReactiveStringRedisTemplate redisTemplate,
                              @Value("${rate.limit.max-requests}") int maxRequests,
                              @Value("${rate.limit.time-window}") int timeWindowSeconds) {
        this.redisTemplate = redisTemplate;
        this.maxRequests = maxRequests;
        this.timeWindow = Duration.ofSeconds(timeWindowSeconds);
    }
    
    public Mono<Boolean> isAllowed(Integer userId) {
        String key = "rate_limit:user:" + userId;
        
        return redisTemplate
                .opsForValue()
                .increment(key)
                .flatMap(count -> {
                    if (count < maxRequests) {
                        return redisTemplate.expire(key, timeWindow).thenReturn(true);
                    } else if (count > maxRequests) {
                        return Mono.just(false);
                    }
                    return Mono.just(true);
                });
    }
}

