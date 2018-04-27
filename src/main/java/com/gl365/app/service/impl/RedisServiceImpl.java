package com.gl365.app.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.gl365.app.service.RedisService;
import com.gl365.app.utils.SerializeUtil;
@Service
public class RedisServiceImpl implements RedisService {
	private final static long LiveTime = 28800L; // 8小时
	@Autowired
	private StringRedisTemplate redis;

	@Override
	public void set(String key, Object value, Long liveTime) {
		redis.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(key.getBytes(), SerializeUtil.serialize(value));
				if (liveTime != null && liveTime > 0) {
					connection.expire(key.getBytes(), liveTime);
				}
				else {
					connection.expire(key.getBytes(), LiveTime);
				}
				return null;
			}
		});
	}

	@Override
	public Object get(String key) {
		return redis.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				try {
					byte[] data = connection.get(key.getBytes());
					if (data != null) { return (Object) SerializeUtil.unserialize(data); }
				}
				catch (Exception e) {
				}
				return null;
			}
		});
	}

	@Override
	public void del(String key) {
		redis.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				try {
					connection.del(key.getBytes());
				}
				catch (Exception e) {
				}
				return null;
			}
		});
	}
}
