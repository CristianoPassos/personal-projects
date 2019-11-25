package de.cristiano.driver.controller;

import de.cristiano.driver.dataaccessobject.car.CarRepository;
import de.cristiano.driver.datatransferobject.DriverDTO;
import de.cristiano.driver.datatransferobject.DriverSearchRequestDTO;
import de.cristiano.driver.domainobject.DriverDO;
import de.cristiano.driver.domainobject.car.CarDO;
import de.cristiano.driver.service.driver.DriverService;
import de.cristiano.driver.util.IntegrationTest;
import io.restassured.http.Header;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DriverControllerTest
{

    public static Long DRIVER_ID_WITHOUT_CAR = 10L;

    public static Long DRIVER_ID_INVALID = -1L;

    public static Long DRIVER_ID_WITH_CAR = 9L;

    public static String CAR_UUID_FREE = "ab1585b3";

    public static String CAR_UUID_SELECTED = "adc123b3";

    @Value("http://localhost:${local.server.port}/v1/drivers/")
    private String baseUrl;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DriverService service;


    @Test
    public void deselectDriverCar_shouldSucceed()
    {
        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
            .header(new Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
            .queryParam("carUuid")
        .when()
            .patch(baseUrl + DRIVER_ID_WITH_CAR)
        .then()
            .statusCode(OK.value())
       ;
        //@Formatter:on

        final DriverDO driverDO = service.find(DRIVER_ID_WITH_CAR);
        final CarDO carDO = carRepository.findByUuid(CAR_UUID_SELECTED).get();

        assertThat(carDO.getDriverId(), is(nullValue()));
        assertThat(carDO.getVersion(), is(2));
    }

    public void searchDriver_shouldSucceed()
    {
        final DriverSearchRequestDTO payload = DriverSearchRequestDTO.builder()
            .driver(DriverDTO.newBuilder().setUsername("driver").createDriverDTO())
            .build();

        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
            .header(new Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
        .when()
            .body(payload)
            .post(baseUrl + "search")
        .then()
            .statusCode(OK.value())
        .body("totalElements", is(10))
        ;
        //@Formatter:on
    }


    @Test
    public void selectDriverCar_carAlreadySelected()
    {
        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
            .header(new Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
            .queryParam("carUuid", CAR_UUID_SELECTED)
        .when()
            .patch(baseUrl + DRIVER_ID_WITHOUT_CAR)
        .then()
            .statusCode(CONFLICT.value())
            .body("message", is("Car: adc123b3 already selected"))
       ;
        //@Formatter:on
    }


    @Test
    public void selectDriverCar_invalidCarUuid()
    {
        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
            .header(new Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
            .queryParam("carUuid", "ajsdja")
        .when()
            .patch(baseUrl + DRIVER_ID_WITHOUT_CAR)
        .then()
            .statusCode(NOT_FOUND.value())
            .body("message", is("Could not locate Car: ajsdja"))
       ;
        //@Formatter:on
    }


    @Test
    public void selectDriverCar_invalidDriver()
    {
        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
            .header(new Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
            .queryParam("carUuid", CAR_UUID_FREE)
        .when()
            .patch(baseUrl + DRIVER_ID_INVALID)
        .then()
            .statusCode(NOT_FOUND.value())
            .body("message", is("Could not locate Driver: -1"))
       ;
        //@Formatter:on
    }


    @Test
    public void selectDriverCar_shouldSucceed()
    {
        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
            .header(new Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
            .queryParam("carUuid", CAR_UUID_FREE)
        .when()
            .patch(baseUrl + DRIVER_ID_WITHOUT_CAR)
        .then()
            .statusCode(OK.value())
       ;
        //@Formatter:on

        final CarDO carDO = carRepository.findByUuid(CAR_UUID_FREE).get();

        assertThat(carDO.getDriverId(), is(DRIVER_ID_WITHOUT_CAR));
    }
}