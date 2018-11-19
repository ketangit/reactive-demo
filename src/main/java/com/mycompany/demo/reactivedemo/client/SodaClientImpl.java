package com.mycompany.demo.reactivedemo.client;

import com.mycompany.demo.reactivedemo.util.Loggable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class SodaClientImpl implements SodaClient, Loggable {
    private final WebClient.Builder webClientBuilder;

    @Override
    public <T> Flux<T> getSodaData(String url, Class<T> responseType, List<String> selectClause, String whereClause, Integer limit) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        if (selectClause != null) {
            builder.queryParam("$select", String.join(",", selectClause));

        } else {
            builder.queryParam("$select", "*");
        }

        if (!StringUtils.isEmpty(whereClause)) {
            builder.queryParam("$where", whereClause);
        }

        if (limit != null) {
            builder.queryParam("$limit", limit);
        }

        URI sodaUri = builder.build().toUri();
        logger().debug(sodaUri.toString());

        return webClientBuilder
                .filter(logRequest())
                .filter(logResposneStatus())
                .build()
                .get()
                .uri(sodaUri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException())
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException())
                )
                .bodyToFlux(responseType);
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger().info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger().info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }

    private ExchangeFilterFunction logResposneStatus() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            logger().info("Response Status {}", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }
}
