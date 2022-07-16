package com.example.laboratoriorestapiwithjwt.storage

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class jwtInterceptor {

    class JWTInterceptor(private val tokenStorage: TokenStorage) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
            val token = tokenStorage.getToken()
            if (token != null) {
                request.addHeader("Authorization", "Bearer $token")
            }
            return chain.proceed(request.build())
        }

    }

   companion object{

       val loggingInterceptor = HttpLoggingInterceptor()
       loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
       val okHttpClient = OkHttpClient.Builder()
           .addInterceptor(loggingInterceptor)
           .addInterceptor(jwtInterceptor)
           .writeTimeout(0, TimeUnit.MILLISECONDS)
           .readTimeout(2, TimeUnit.MINUTES)
           .connectTimeout(1, TimeUnit.MINUTES).build()

       val gson = GsonBuilder()
           .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
           .create()

       return Retrofit.Builder()
       .baseUrl("https://dog.ceo/api/")
       .client(okHttpClient)
       .addConverterFactory(GsonConverterFactory.create(gson))
       .addCallAdapterFactory(CoroutineCallAdapterFactory())
       .build()


   }

}