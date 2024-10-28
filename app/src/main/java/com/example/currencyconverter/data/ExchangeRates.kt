package com.example.currencyconverter.data

object ExchangeRates {
    val rates = mapOf(
        // Tỷ giá USD
        "USD_VND" to 25355.00,
        "VND_USD" to 0.000039,
        "USD_EUR" to 0.925,
        "EUR_USD" to 1.081,
        "USD_JPY" to 148.23,
        "JPY_USD" to 0.0067,
        "USD_GBP" to 0.82,
        "GBP_USD" to 1.22,

        // Tỷ giá EUR
        "EUR_VND" to 27375.00,  // Tỷ giá EUR sang VND
        "VND_EUR" to 0.0000365,
        "EUR_JPY" to 159.77,     // Tỷ giá EUR sang JPY
        "JPY_EUR" to 0.00625,
        "EUR_GBP" to 0.89,      // Tỷ giá EUR sang GBP
        "GBP_EUR" to 1.12,

        // Tỷ giá JPY
        "JPY_VND" to 171.70,    // Tỷ giá JPY sang VND
        "VND_JPY" to 0.00581,
        "JPY_GBP" to 0.0055,    // Tỷ giá JPY sang GBP
        "GBP_JPY" to 181.75,

        // Tỷ giá GBP
        "GBP_VND" to 30800.00,  // Tỷ giá GBP sang VND
        "VND_GBP" to 0.0000325,

        // Các loại tiền tệ khác (nếu có)
        "USD_AUD" to 1.50,      // Tỷ giá USD sang AUD
        "AUD_USD" to 0.67,
        "USD_CAD" to 1.36,      // Tỷ giá USD sang CAD
        "CAD_USD" to 0.74,
        "USD_CHF" to 0.91,      // Tỷ giá USD sang CHF
        "CHF_USD" to 1.10,
        "AUD_VND" to 16925.00,  // Tỷ giá AUD sang VND
        "CAD_VND" to 18600.00,  // Tỷ giá CAD sang VND
        "CHF_VND" to 23000.00   // Tỷ giá CHF sang VND
    )
}
