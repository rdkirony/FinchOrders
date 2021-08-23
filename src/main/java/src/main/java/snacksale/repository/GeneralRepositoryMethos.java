package src.main.java.snacksale.repository;

import java.util.UUID;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class GeneralRepositoryMethos {


	@SuppressWarnings("unchecked")
	public GeneralRepositoryMethos(RedisTemplate redisTemplate) {
		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
	}


	public String formatKey(String guid) {
		return guid.replace("-", "");
	}

	public String generateId() {
		UUID uuid = UUID.randomUUID();
		return formatKey(uuid.toString());
	}
}
