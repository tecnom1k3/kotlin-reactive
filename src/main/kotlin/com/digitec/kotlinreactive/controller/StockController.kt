package com.digitec.kotlinreactive.controller

import com.digitec.kotlinreactive.dto.CompanyProfile
import com.digitec.kotlinreactive.dto.CompanyRatios
import com.digitec.kotlinreactive.dto.Symbol
import com.digitec.kotlinreactive.service.StockService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class StockController(var stockService: StockService) {

    @GetMapping("/stock/list")
    fun getSymbolList(): ArrayList<Symbol> {
        return stockService.getSymbolList()
    }

    @GetMapping("/symbol/{symbol}")
    fun getProfile(@PathVariable symbol: String): CompanyProfile? {
        return stockService.getProfile(symbol)
    }

    @GetMapping("/symbol/{symbol}/ratios")
    fun getRatios(@PathVariable symbol: String): CompanyRatios? {
        return stockService.getRatios(symbol)
    }
}