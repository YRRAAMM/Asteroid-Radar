package com.udacity.asteroidradar.asteroidRepository.api.APIBuilder

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.asteroidRepository.api.APIService
import com.udacity.asteroidradar.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

object Network {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}
