package de.cristiano.driver.controller.car;

import de.cristiano.driver.datatransferobject.CarDTO;
import de.cristiano.driver.datatransferobject.CarSearchRequestDTO;
import de.cristiano.driver.domainobject.car.CarDO;
import de.cristiano.driver.service.car.CarService;
import de.cristiano.driver.util.IntegrationTest;
import de.cristiano.driver.controller.DriverControllerTest;
import io.restassured.http.Header;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static de.cristiano.driver.util.JsonUtils.toJson;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CarControllerTest
{
    private static final String CAR_UUID_INVALID = "aaaaaaa";

    private static final String CAR_UUID_VALID = "ab1585b3";

    @Value("http://localhost:${local.server.port}/v1/car/")
    private String baseUrl;

    @Autowired
    private CarService service;


    @Test
    public void createCar_badRequest()
    {
        final CarDTO payload = CarDTO.builder()
            .convertible(false)
            .seatCount(9)
            .licensePlate("U BC 14 95")
            .engineTypeId(1L)
            .manufacturerId(1L)
            .build();

        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
            .header(new Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
        .when()
            .body(payload)
            .post(baseUrl)
        .then()
            .statusCode(BAD_REQUEST.value())
            .body("message", is("Invalid seat counts, please use one of: [3, 5, 8]"))
       ;
        //@Formatter:on
    }


    @Test
    public void createCar_shouldSucceed()
    {
        final CarDTO payload = CarDTO.builder()
            .convertible(false)
            .seatCount(5)
            .licensePlate("U BC 14 95")
            .engineTypeId(1L)
            .manufacturerId(1L)
            .build();

        final String newCarUuid =

            //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
            .header(new Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
        .when()
            .body(payload)
            .post(baseUrl)
        .then()
            .statusCode(CREATED.value())
            .extract().path("uuid");
        //@Formatter:on

        final CarDO carDO = service.findByUuid(newCarUuid)
            .get();

        assertThat(carDO.getDeleted(), is(false));
        assertThat(carDO.getConvertible(), is(payload.getConvertible()));
        assertThat(carDO.getSeatCount(), is(payload.getSeatCount()));
        assertThat(carDO.getLicensePlate(), is(payload.getLicensePlate()));
        assertThat(carDO.getEngineTypeId(), is(payload.getEngineTypeId()));
        assertThat(carDO.getManufacturerId(), is(payload.getManufacturerId()));
        assertThat(carDO.getDateCreated(), is(notNullValue()));
    }


    @Test
    public void searchCar_shouldSucceed()
    {
        final CarSearchRequestDTO payload = CarSearchRequestDTO.builder()
            .car(CarDTO.builder().driverId(DriverControllerTest.DRIVER_ID_WITH_CAR).build())
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
        .body("totalElements", is(1))
        ;
        //@Formatter:on
    }


    @Test
    public void deleteCar_notFound()
    {
        //@Formatter:off
        given()
            .header(new Header(ACCEPT, TEXT_PLAIN_VALUE))
        .when()
            .delete(baseUrl + CAR_UUID_INVALID)
        .then()
            .statusCode(NOT_FOUND.value())
            .body("message", is("Could not find Car: aaaaaaa"))
        ;
        //@Formatter:on
    }


    @Test
    public void deleteCar_shouldSucceed()
    {
        //@Formatter:off
        given()
            .header(new Header(ACCEPT, TEXT_PLAIN_VALUE))
        .when()
            .delete(baseUrl + CAR_UUID_VALID)
        .then()
            .statusCode(OK.value())
        ;
        //@Formatter:on

        final CarDO car = service.findByUuid(CAR_UUID_VALID)
            .get();

        assertThat(car.getDeleted(), is(true));
    }


    @Test
    public void getCar_notFound()
    {
        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
        .when()
            .get(baseUrl + CAR_UUID_INVALID)
        .then()
            .statusCode(NOT_FOUND.value())
            .body("message", is("Could not find Car: aaaaaaa"))
        ;
        //@Formatter:on
    }


    @Test
    public void getCar_notSupported()
    {
        //@Formatter:off
        given()
            .header(new Header(ACCEPT, TEXT_PLAIN_VALUE))
        .when()
            .get(baseUrl + CAR_UUID_VALID)
        .then()
            .statusCode(NOT_ACCEPTABLE.value())
        ;
        //@Formatter:on
    }


    @Test
    public void getCar_shouldSucceed()
    {
        final CarDTO carDTO = CarDTO.builder()
            .licensePlate("RA KL 8136")
            .seatCount(5)
            .convertible(false)
            .engineTypeId(2L)
            .manufacturerId(1L)
            .uuid(CAR_UUID_VALID)
            .build();

        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
        .when()
            .get(baseUrl + CAR_UUID_VALID)
        .then()
            .statusCode(OK.value())
        .body(sameJSONAs(toJson(carDTO)));
        //@Formatter:on
    }


    @Test
    public void updateCar_shouldSucceed()
    {
        final CarDTO payload = CarDTO.builder()
            .convertible(true)
            .seatCount(8)
            .licensePlate("A CB 19 85")
            .engineTypeId(2L)
            .manufacturerId(2L)
            .build();

        //@Formatter:off
        given()
            .header(new Header(ACCEPT, APPLICATION_JSON_VALUE))
            .header(new Header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
        .when()
            .body(payload)
            .put(baseUrl + CAR_UUID_VALID)
        .then()
            .statusCode(OK.value())
        ;
        //@Formatter:on

        final CarDO carDO = service.findByUuid(CAR_UUID_VALID)
            .get();

        assertThat(carDO.getDeleted(), is(false));
        assertThat(carDO.getConvertible(), is(payload.getConvertible()));
        assertThat(carDO.getSeatCount(), is(payload.getSeatCount()));
        assertThat(carDO.getLicensePlate(), is(payload.getLicensePlate()));
        assertThat(carDO.getEngineTypeId(), is(payload.getEngineTypeId()));
        assertThat(carDO.getManufacturerId(), is(payload.getManufacturerId()));
    }
}
