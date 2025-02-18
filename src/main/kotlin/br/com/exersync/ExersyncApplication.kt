package br.com.exersync

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExersyncApplication

fun main(args: Array<String>) {
    runApplication<ExersyncApplication>(*args)
}
