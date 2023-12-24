package accuweather.HomeWork;

import accuweather.AccuweatherAbstractTest;
import org.example.seminar.accuweather.location.Location;
import org.example.seminar.accuweather.weather.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class ForecastsOneDaysTest extends AccuweatherAbstractTest {
    @Test
    void testGetResponseOneDays(){
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/1day/{locationKey}")
                .then().statusCode(200).time(lessThan(2000L))
                .extract().response().body().as(Weather.class);
        Assertions.assertEquals(1, weather.getDailyForecasts().size());
        System.out.println(weather);
    }

}
