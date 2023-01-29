package com.udacity.asteroidradar.asteroidRepository.api.APIBuilder

import com.squareup.moshi.Moshi
import com.udacity.asteroidradar.asteroidRepository.api.APIService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Network private constructor(private val retrofit: Retrofit){
    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }

    companion object {
        fun create(baseUrl: String, moshi: Moshi): Network {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return Network(retrofit)
        }
    }
}

