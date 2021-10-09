package com.example.currencyconverterapp

import com.google.gson.annotations.SerializedName

class currencyList {

    @SerializedName("date")
    var data: String? = null


    @SerializedName("eur")
    var EUR: cerrencey? = null

    class cerrencey {

        @SerializedName("inr")
        var INR: String? = null

        @SerializedName("usd")
        var USD: String? = null

        @SerializedName("aud")
        var AUD: String? = null

        @SerializedName("sar")
        var SAR: String? = null

        @SerializedName("cny")
        var CNY: String? = null

        @SerializedName("jpy")
        var JPY: String? = null


    }}