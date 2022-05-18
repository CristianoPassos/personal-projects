package exercise.util

import com.fasterxml.jackson.dataformat.csv.CsvSchema
import exercise.config.ObjectMapperConfig
import java.io.FileNotFoundException
import java.io.FileReader

object FileUtils {
    fun getAbsolutePath(pathFromSource: String): String = this::class.java.getResource(pathFromSource)?.path
        ?: throw FileNotFoundException("File $pathFromSource could not be found")

    fun <T> parseFile(pathFromSource: String, clazz: Class<T>): MutableSet<T> {
        return FileReader(getAbsolutePath(pathFromSource)).use { reader ->
            ObjectMapperConfig.CSV_MAPPER
                .readerFor(clazz)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues<T>(reader)
                .readAll()
                .toMutableSet()
        }
    }
}
