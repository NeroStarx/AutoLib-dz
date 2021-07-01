package com.clovertech.autolibdz.utils

import api.AuthenticationApi
import api.BorneApi
import api.RegistrationApi
import com.clovertech.autolibdz.APIs.*
import com.clovertech.autolibdz.utils.Constants.Companion.Bill_BASE_URL
import com.clovertech.autolibdz.utils.Constants.Companion.CARD_BASE_URL
import com.clovertech.autolibdz.utils.Constants.Companion.Cars_BASE_URL
import com.clovertech.autolibdz.utils.Constants.Companion.Pricing_BASE_URL
import com.clovertech.autolibdz.utils.Constants.Companion.Rental_BASE_URL
import com.clovertech.autolibdz.utils.Constants.Companion.SUB_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    fun retrofitInstance(url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    val authenticationApi : AuthenticationApi by lazy {
        retrofitInstance(baseUrl+"auth/").create(AuthenticationApi::class.java)
    }

    val registrationApi: RegistrationApi by lazy {
        retrofitInstance(baseUrl+"auth/").create(RegistrationApi::class.java)

    }

    val borneApi: BorneApi by lazy {
        retrofitInstance(baseUrl+"bornes/").create(BorneApi::class.java)
    }

    private val retrofitGetCard by lazy {
        Retrofit.Builder()
                .baseUrl(Cars_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    private val retrofitGetPring by lazy {
        Retrofit.Builder()
                .baseUrl(Pricing_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    private val retrofitGetCards by lazy {
        Retrofit.Builder()
                .baseUrl(CARD_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    private val retrofitRental by lazy {
        Retrofit.Builder()
                .baseUrl(Rental_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    val AddRentalApi: RentalApi by lazy {
        retrofitRental.create(RentalApi::class.java)
    }
    private val retrofitAddCard by lazy {
        Retrofit.Builder()
                .baseUrl(Cars_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
        val retrofitReduPrice:PromoApi by lazy {
        Retrofit.Builder()
            .baseUrl(Pricing_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PromoApi::class.java)
    }
    val api : PayApi by lazy {
        retrofitGetCards.create(PayApi::class.java)
    }
    val subApi:PayApi by lazy {
        Retrofit.Builder().baseUrl(SUB_BASE_URL).addConverterFactory(
                GsonConverterFactory.create()).build().create(PayApi::class.java)

    }
    val factureApi:FactureApi by lazy {
        Retrofit.Builder().baseUrl(Bill_BASE_URL).addConverterFactory(
                GsonConverterFactory.create()).build().create(FactureApi::class.java)

    }
}