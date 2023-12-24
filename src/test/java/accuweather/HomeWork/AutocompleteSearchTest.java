package accuweather.HomeWork;

import accuweather.AccuweatherAbstractTest;
import org.example.seminar.accuweather.location.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AutocompleteSearchTest extends AccuweatherAbstractTest {
    @Test
    void testGetLocations(){
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("apikey", getApiKey());
        mapQuery.put("q", "Murmansk");
        List<Location> locationList = given().queryParams(mapQuery)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200)
                .extract().body().jsonPath().getList(".", Location.class);
        Assertions.assertAll(()->Assertions.assertEquals(1, locationList.size()),
                ()->Assertions.assertEquals("Murmansk", locationList.get(0).getLocalizedName()));


    }
}
