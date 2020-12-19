package com.digitec.kotlinreactive.dto

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class CompanyRatios(
        val assetTurnover: Double,
        val capitalExpenditureCoverageRatio: Double,
        val cashConversionCycle: Double,
        val cashFlowCoverageRatios: Double,
        val cashFlowToDebtRatio: Double,
        val cashPerShare: Double,
        val cashRatio: Double,
        val companyEquityMultiplier: Double,
        val currentRatio: Double,
        val date: String,
        val daysOfInventoryOutstanding: Double,
        val daysOfPayablesOutstanding: Double,
        val daysOfSalesOutstanding: Double,
        val debtEquityRatio: Double,
        val debtRatio: Double,
        val dividendPaidAndCapexCoverageRatio: Double,
        val dividendPayoutRatio: Double,
        val dividendYield: Double,
        val ebitPerRevenue: Double,
        val ebtPerEbit: Int,
        val effectiveTaxRate: Double,
        val enterpriseValueMultiple: Double,
        val fixedAssetTurnover: Double,
        val freeCashFlowOperatingCashFlowRatio: Double,
        val freeCashFlowPerShare: Double,
        val grossProfitMargin: Double,
        val interestCoverage: Double,
        val inventoryTurnover: Double,
        val longTermDebtToCapitalization: Double,
        val netIncomePerEBT: Double,
        val netProfitMargin: Double,
        val operatingCashFlowPerShare: Double,
        val operatingCashFlowSalesRatio: Double,
        val operatingCycle: Double,
        val operatingProfitMargin: Double,
        val payablesTurnover: Double,
        val payoutRatio: Double,
        val pretaxProfitMargin: Double,
        val priceBookValueRatio: Double,
        val priceCashFlowRatio: Double,
        val priceEarningsRatio: Double,
        val priceEarningsToGrowthRatio: Double,
        val priceFairValue: Double,
        val priceSalesRatio: Double,
        val priceToBookRatio: Double,
        val priceToFreeCashFlowsRatio: Double,
        val priceToOperatingCashFlowsRatio: Double,
        val priceToSalesRatio: Double,
        val quickRatio: Double,
        val receivablesTurnover: Double,
        val returnOnAssets: Double,
        val returnOnCapitalEmployed: Double,
        val returnOnEquity: Double,
        val shortTermCoverageRatios: Double,
        val symbol: String,
        val totalDebtToCapitalization: Double
) {
    class Deserializer : ResponseDeserializable<Array<CompanyRatios>> {
        override fun deserialize(content: String): Array<CompanyRatios>? = Gson().fromJson(content, Array<CompanyRatios>::class.java)
    }
}