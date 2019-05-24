package app.bwizlabs.dayshout

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DayshoutApplication

fun main(args: Array<String>) {
    runApplication<DayshoutApplication>(*args)
}
