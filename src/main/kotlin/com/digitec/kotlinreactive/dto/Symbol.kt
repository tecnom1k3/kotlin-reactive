package com.digitec.kotlinreactive.dto

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Symbol(var symbol: String, var name: String, var price: Double, var exchange: String) {
    class Deserializer : ResponseDeserializable<Array<Symbol>> {
        override fun deserialize(content: String): Array<Symbol>? = Gson().fromJson(content, Array<Symbol>::class.java)
    }
}