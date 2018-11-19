package com.mycompany.demo.reactivedemo.api;

import com.mycompany.demo.reactivedemo.client.SodaClient;
import com.mycompany.demo.reactivedemo.model.Farmer;
import com.mycompany.demo.reactivedemo.model.Liquor;
import com.mycompany.demo.reactivedemo.model.Subway;
import com.mycompany.demo.reactivedemo.model.Tree;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {
    private final SodaClient sodaClient;

    @GetMapping("/farmer")
    public Flux<Farmer> farmer() {
        String sodaUrl = "https://data.ct.gov/resource/y6p2-px98.json";
        List<String> selectClause = Arrays.asList("farmer_id", "website", "suite", "category", "business", "item", "location_1", "location_1_city", "location_1_location", "location_1_state", "zipcode");
        String whereClause = null;
        int limit = 20;
        return sodaClient.getSodaData(sodaUrl, Farmer.class, selectClause, whereClause, limit);
    }

    @GetMapping("/liquor")
    public Flux<Liquor> liquor() {
        //https://dev.socrata.com/foundry/data.ny.gov/j7kd-fzm7
        String sodaUrl = "https://data.ny.gov/resource/j7kd-fzm7.json";
        List<String> selectClause = Arrays.asList("brand_label_expiration_date", "brand_label_name", "brand_label_serial_number", "domestic_d_or_imported_i", "license_class_code", "license_class_description", "license_type_code", "product_description", "wholesaler_license_serial_number", "wholesaler_name");
        String whereClause = null;
        int limit = 20;
        return sodaClient.getSodaData(sodaUrl, Liquor.class, selectClause, whereClause, limit);
    }

    @GetMapping("/subway")
    public Flux<Subway> subway() {
        //https://dev.socrata.com/foundry/data.ny.gov/hvwh-qtfg
        String sodaUrl = "https://data.ny.gov/resource/hvwh-qtfg.json";
        List<String> selectClause = Arrays.asList("entrance_type", "entry", "free_crossover", "line", "north_south_street", "route1", "staffing", "station_name", "station_location", "entrance_location", "division", "vending");
        String whereClause = null;
        int limit = 20;
        return sodaClient.getSodaData(sodaUrl, Subway.class, selectClause, whereClause, limit);
    }

    @GetMapping("/tree")
    public Flux<Tree> tree() {
        String sodaUrl = "https://data.sfgov.org/resource/2zah-tuvt.json";
        List<String> selectClause = Arrays.asList("treeid", "plantdate", "qaddress", "qlegalstatus", "qsiteinfo", "qspecies", "planttype", "siteorder");
        String whereClause = null;
        int limit = 20;
        return sodaClient.getSodaData(sodaUrl, Tree.class, selectClause, whereClause, limit);
    }
}
