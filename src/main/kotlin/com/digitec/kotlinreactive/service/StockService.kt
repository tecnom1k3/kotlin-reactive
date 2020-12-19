package com.digitec.kotlinreactive.service

import com.digitec.kotlinreactive.configuration.AppProperties
import com.digitec.kotlinreactive.dto.CompanyProfile
import com.digitec.kotlinreactive.dto.CompanyRatios
import com.digitec.kotlinreactive.dto.Symbol
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class StockService {

    @Autowired
    private lateinit var appProperties: AppProperties

    private val logger = LoggerFactory.getLogger(javaClass)

    @Cacheable("symbols")
    fun getSymbolList(): ArrayList<Symbol> {

        logger.info("Getting symbol list from web")

        FuelManager.instance.basePath = "https://financialmodelingprep.com"

        val symbols = ArrayList<Symbol>()

        val url = "/api/v3/stock/list?apikey=" + appProperties.apiKey;

        val (_, _, result) = url.httpGet().responseObject(Symbol.Deserializer())

        when (result) {
            is Result.Failure -> {
                logger.error(result.getException().toString())
            }

            is Result.Success -> {
                logger.info("Got result from web")
                val (symbolResult, _) = result
                symbolResult?.filter {
                    it.exchange != null
                            && it.name != null
                            && it.exchange !in listOf(
                            "Paris",
                            "Toronto",
                            "Amsterdam",
                            "BATS",
                            "BATS Exchange",
                            "Brussels",
                            "Lisbon",
                            "Swiss",
                            "YHD",
                            "NYSE Arca",
                            "NYSEArca",
                            "Nasdaq"
                    )
                            && !it.name.contains("ADR")
                            && !it.symbol.contains(".")
                }?.let { symbols.addAll(it) }
            }
        }

        return symbols
    }

    @Cacheable(value = ["profiles"], key = "#symbol")
    fun getProfile(symbol: String): CompanyProfile? {
        logger.info("Getting [$symbol] profile from web")

        var companyProfile: CompanyProfile? = null
        FuelManager.instance.basePath = "https://financialmodelingprep.com"

        val url = "/api/v3/profile/" + symbol.toUpperCase() + "?apikey=" + appProperties.apiKey;

        val (_, _, result) = url.httpGet().responseObject(CompanyProfile.Deserializer())

        when (result) {
            is Result.Failure -> {
                logger.error(result.getException().toString())
            }

            is Result.Success -> {
                logger.info("Got result from web")
                val (symbolProfileResult, _) = result
                val plainSymbolProfile = symbolProfileResult?.get(0)
                if (plainSymbolProfile != null) {
                    companyProfile = plainSymbolProfile
                }
            }
        }

        return companyProfile
    }

    @Cacheable(value = ["ratios"], key = "#symbol")
    fun getRatios(symbol: String): CompanyRatios? {
        logger.info("Getting [$symbol] ratios from web")

        var companyRatios: CompanyRatios? = null
        FuelManager.instance.basePath = "https://financialmodelingprep.com"

        val url = "/api/v3/ratios/" + symbol.toUpperCase() + "?apikey=" + appProperties.apiKey;

        val (_, _, result) = url.httpGet().responseObject(CompanyRatios.Deserializer())

        when (result) {
            is Result.Failure -> {
                logger.error(result.getException().toString())
            }

            is Result.Success -> {
                logger.info("Got result from web")
                val (symbolRatiosResult, _) = result
                val plainSymbolRatios = symbolRatiosResult?.get(0)
                if (plainSymbolRatios != null) {
                    companyRatios = plainSymbolRatios
                }
            }
        }

        return companyRatios
    }
}