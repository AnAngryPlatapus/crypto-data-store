package com.sunny.cds

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class CryptoDataStoreApplication

fun main(args: Array<String>) {
	runApplication<CryptoDataStoreApplication>(*args)
}
