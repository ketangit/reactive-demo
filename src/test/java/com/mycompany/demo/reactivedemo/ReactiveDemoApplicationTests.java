package com.mycompany.demo.reactivedemo;

import com.mycompany.demo.reactivedemo.client.SodaClient;
import com.mycompany.demo.reactivedemo.model.Subway;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReactiveDemoApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SodaClient sodaClient;

    @Test
    public void testSubway() {
        List<Subway> subwayList = new ArrayList<>();

        Subway subway1 = new Subway();
        subway1.setEntry("south");
        subway1.setLine("red-line");
        Subway subway2 = new Subway();
        subway2.setEntry("north");
        subway2.setLine("green-line");

        subwayList.add(subway1);
        subwayList.add(subway2);

        Flux<Subway> subwayFlux = Flux.fromIterable(subwayList);
        given(sodaClient.getSodaData("anyUrl", Subway.class, null, "anyString", 2)).willReturn(subwayFlux);

        webTestClient
                .get()
                .uri("/api/subway")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Subway.class);
    }
}
