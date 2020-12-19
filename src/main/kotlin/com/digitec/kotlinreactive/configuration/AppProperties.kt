package com.digitec.kotlinreactive.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProperties {
    @Value("\${financialmodelingprep.apiKey}")
    lateinit var apiKey: String
}