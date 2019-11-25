package de.cristiano.driver.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.cristiano.driver.domainvalue.GeoCoordinate;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO
{
    private GeoCoordinate coordinate;

    @JsonIgnore
    private Long id;

    @NotNull(message = "Password can not be null!")

    private String password;

    @NotNull(message = "Username can not be null!")
    private String username;


    private DriverDTO()
    {
    }


    private DriverDTO(Long id, String username, String password, GeoCoordinate coordinate)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
    }


    public static DriverDTOBuilder newBuilder()
    {
        return new DriverDTOBuilder();
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getPassword()
    {
        return password;
    }


    public String getUsername()
    {
        return username;
    }

    public static class DriverDTOBuilder
    {
        private GeoCoordinate coordinate;
        private Long id;
        private String password;
        private String username;


        public DriverDTO createDriverDTO()
        {
            return new DriverDTO(id, username, password, coordinate);
        }


        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate)
        {
            this.coordinate = coordinate;
            return this;
        }


        public DriverDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public DriverDTOBuilder setPassword(String password)
        {
            this.password = password;
            return this;
        }


        public DriverDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }

    }
}
