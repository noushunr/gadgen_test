package com.example.gadgeontest.data.network

import com.example.gadgeontest.data.model.User
import com.example.gadgeontest.utils.BASE_API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


/**
 * Created by Noushad N on 06-06-2022.
 */

interface MyApi {

    @GET("users")
    suspend fun getUsersList(
        @Query("page") page : Int

    ) : Response<User>


    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .callTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build()
//            okkHttpclient?.newBuilder()?.addInterceptor { chain ->
//                val request: Request =
//                    chain.request().newBuilder().addHeader("api-key", "DEMO_KEY")
//                        .build()
//                chain.proceed(request)
//            }

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BASE_API_URL)
//                .baseUrl(TEST_BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}