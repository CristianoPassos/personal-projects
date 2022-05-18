package exercise.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies.UPPER_SNAKE_CASE
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class ObjectMapperConfig {

    companion object {
        val CSV_MAPPER: ObjectMapper = CsvMapper()
            .apply { registerModule(KotlinModule.Builder().build()) }
            .setPropertyNamingStrategy(UPPER_SNAKE_CASE)
    }

}