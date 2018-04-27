package com.gl365.app.service;
public interface RedisService {
	public abstract void set(String key, Object value, Long liveTime);

	public abstract Object get(String key);

	public abstract void del(String key);
}
