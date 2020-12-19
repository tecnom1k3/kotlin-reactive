package com.digitec.kotlinreactive.dto

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class CompanyProfile(
        val address: String,
        val beta: Double,
        val ceo: String,
        val changes: Double,
        val city: String,
        val companyName: String,
        val country: String,
        val dcf: Double,
        val dcfDiff: Double,
        val description: String,
        val exchange: String,
        val exchangeShortName: String,
        val fullTimeEmployees: String,
        val image: String,
        val industry: String,
        val lastDiv: Double,
        val mktCap: Long,
        val phone: String,
        val price: Double,
        val range: String,
        val sector: String,
        val state: String,
        val symbol: String,
        val volAvg: Int,
        val website: String,
        val zip: String
) {
    class Deserializer : ResponseDeserializable<Array<CompanyProfile>> {
        override fun deserialize(content: String): Array<CompanyProfile>? = Gson().fromJson(content, Array<CompanyProfile>::class.java)
    }
}