package com.polytech.applicationcinma

import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.polytech.applicationcinma.model.*
import com.squareup.moshi.Json
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


private const val BASE_URL = "http://cinema.erebz.fr:80/"


private var retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface Api {

    //LOGIN & REGISTER
    @POST("login")
    fun login(@Body logInfo: Utilisateur) : Deferred<loginRet>

    @POST("register")
    fun register(@Body regInfo: Utilisateur) : Deferred<loginRet>


    //GET LISTS
    @GET("films/list")
    fun getFilms() : Deferred<List<Film>>

    @GET("acteurs/list")
    fun getActors() : Deferred<List<Acteur>>

    @GET("realisateurs/list")
    fun getReals() : Deferred<List<Realisateur>>

    @GET("personnages/list")
    fun getPersons() : Deferred<List<Personnage>>


    //ITEM DATA
    @GET("films/{NoFilm}")
    fun getFilm(@Path("NoFilm") NoFilm: Int): Deferred<Film>

    @GET("acteurs/{NoActor}")
    fun getActor(@Path("NoActor") NoActor: Int): Deferred<Acteur>

    @GET("realisateurs/{NoReal}")
    fun getReal(@Path("NoReal") NoReal: Int): Deferred<Realisateur>

    @GET("personnages/{NoPerson}")
    fun getPerson(@Path("NoPerson") NoPerson: Int): Deferred<Personnage>

}

object MyApi {
    val retrofitService : Api by lazy {

        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging) // <-- this is the important line!

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient.build())
            .build()
        retrofit.create(Api::class.java) }
}

data class loginRet(
    @Json(name="token") val token: String,
    @Json(name="response") val response: Boolean
)