package com.mycompany.demo.reactivedemo.client;

import reactor.core.publisher.Flux;

import java.util.List;

public interface SodaClient {
    <T> Flux<T> getSodaData(String url, Class<T> responseType, List<String> selectClause, String whereClause, Integer limit);
}
