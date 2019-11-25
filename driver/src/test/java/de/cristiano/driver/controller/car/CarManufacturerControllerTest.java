package de.cristiano.driver.controller.car;

import de.cristiano.driver.controller.mapper.car.CarManufacturerMapper;
import de.cristiano.driver.dataaccessobject.car.CarManufacturerRepository;
import de.cristiano.driver.util.IntegrationTest;
import io.restassured.http.Header;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static de.cristiano.driver.controller.mapper.ListMapper.makeDTOList;
import static de.cristiano.driver.util.JsonUtils.toJson;
import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CarManufacturerControllerTest
{
    @Value("http://localhost:${local.server.port}/v1/car/manufacturer/")
    private String baseUrl;

    @Autowired
    private CarManufacturerRepository repository;


    @Test
    public void getAllCarManufacturer_shouldSucceed()
    {
        final String carManufacturersJson = toJson(makeDTOList(repository.findAll(), CarManufacturerMapper::makeDTO));

        //@Formatter:off
        given()
            .header(new Header(ACCEPT, MediaType.APPLICATION_JSON_VALUE))
        .when()
            .get(baseUrl + "all")
        .then()
            .statusCode(HttpStatus.OK.value())
        .body(sameJSONAs(carManufacturersJson).allowingAnyArrayOrdering())
        ;
        //@Formatter:on
    }
}
