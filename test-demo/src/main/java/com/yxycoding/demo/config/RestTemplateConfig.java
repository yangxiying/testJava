package com.yxycoding.demo.config;/*
 * @author yangxy
 * @date 2020/8/17 14:58
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplatea = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplatea.getMessageConverters();
        messageConverters.add(new BufferedImageHttpMessageConverter());
//        messageConverters.add()
//        for (HttpMessageConverter<?> messageConverter : messageConverters) {
//
//            List<MediaType> supportedMediaTypes = messageConverter.getSupportedMediaTypes();
//            supportedMediaTypes.add(MediaType.IMAGE_GIF);
//            supportedMediaTypes.add(MediaType.IMAGE_JPEG);
//            supportedMediaTypes.add(MediaType.IMAGE_PNG);
//
//        }

//        return builder.build();
        return restTemplatea;
//        return new RestTemplate();
    }

}
