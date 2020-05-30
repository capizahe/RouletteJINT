package com.masivian.cleancode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.masivian.cleancode.model.Bet;
import com.masivian.cleancode.model.Roulette;

@Configuration
public class ReddisConfigurartion {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String, Roulette> redisTemplate(){
		RedisTemplate<String, Roulette> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
	
	@Bean
	RedisTemplate<String, Bet> redisTemplate2(){
		RedisTemplate<String, Bet> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
}
