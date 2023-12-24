package accuweather.HomeWork;

import accuweather.AccuweatherAbstractTest;
import org.example.seminar.accuweather.weather.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class ForecastsTenDaysTest extends AccuweatherAbstractTest {
    @Test
    void testGetResponseTenDays(){
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/10day/{locationKey}")
                .then().statusCode(200).time(lessThan(2000L))
                .extract().response().body().as(Weather.class);
        Assertions.assertEquals(10, weather.getDailyForecasts().size());
        System.out.println(weather);
    }
}
