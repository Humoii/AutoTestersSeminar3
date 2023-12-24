package accuweather;

import org.example.seminar.accuweather.location.Location;
import org.example.seminar.accuweather.weather.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class ForecastsFiveDaysTest extends AccuweatherAbstractTest{
    @Test
    void testGetResponseFiveDays(){
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{locationKey}")
                .then().statusCode(200).time(lessThan(2000L))
                .extract().response().body().as(Weather.class);
        Assertions.assertEquals(5, weather.getDailyForecasts().size());
        System.out.println(weather);
    }

    @Test
    void testGetLocations(){
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("apikey", getApiKey());
        mapQuery.put("q", "Samara");
        List<Location> locationList = given().queryParams(mapQuery)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200)
                .extract().body().jsonPath().getList(".", Location.class);
        Assertions.assertAll(()->Assertions.assertEquals(10, locationList.size()),
                ()->Assertions.assertEquals("Samarai", locationList.get(2).getLocalizedName()));


    }
}
