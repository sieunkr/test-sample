package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final RedisTemplate<String, Coffee> coffeeRedisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        coffeeRedisTemplate.opsForHash().put("key", "00", Coffee.builder()
                .name("americano")
                .price(1200)
                .test(Arrays.asList(10L, 11L, 12L))
                .build());
        coffeeRedisTemplate.opsForHash().put("key", "01", Coffee.builder().name("latte").price(1100).build());



        coffeeRedisTemplate.opsForHash().get("key", "01");

        /*
        Map<Object, Object> val = coffeeRedisTemplate.opsForHash().entries("key");

        List<Coffee> list = val.keySet().stream().map(k -> (Coffee)val.get(k)).collect(Collectors.toList());

        log.info("test");
        */

        Integer a = ((Coffee)coffeeRedisTemplate.opsForHash().get("key", "01")).getPrice();
        Integer b = ((Coffee)coffeeRedisTemplate.opsForHash().get("key", "00")).getPrice();


        Object object = coffeeRedisTemplate.opsForHash().get("key", "03");

        if(!ObjectUtils.isEmpty(object)) {
            
        }

        Integer c = ((Coffee)coffeeRedisTemplate.opsForHash().get("key", "03")).getPrice();

        log.info(String.valueOf(a - b));

    }
}
