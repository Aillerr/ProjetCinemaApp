package com.polytech.applicationcinma

import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.polytech.applicationcinma.model.*
import com.squareup.moshi.Json
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private const val BASE_URL = "http://cinema.erebz.fr:80/"


private var retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface Api {

    //LOGIN & REGISTER
    @Headers("Content-Type: application/json")
    @POST("authentification/login")
    fun login(@Body logInfo: LoginInfo) : Deferred<loginRet>

    @POST("authentification/register")
    fun register(@Body regInfo: LoginInfo) : Deferred<Response<Void>>


    //GET LISTS
    @GET("films/list")
    fun getFilms(@Header("Authorization") authHeader: String) : Deferred<List<PersoFilmList>>

    @GET("acteurs/list")
    fun getActors(@Header("Authorization") authHeader: String) : Deferred<List<PersoActeurList>>

    @GET("realisateurs/list")
    fun getReals(@Header("Authorization") authHeader: String) : Deferred<List<PersoRealList>>

    @GET("personnages/list")
    fun getPersons(@Header("Authorization") authHeader: String) : Deferred<List<PersoPersonList>>


    //ITEM DATA
    @GET("films/{NoFilm}")
    fun getFilm(@Path("NoFilm") NoFilm: Int, @Header("Authorization") authHeader: String): Deferred<PersoFilmList>

    @GET("acteurs/{NoActor}")
    fun getActor(@Path("NoActor") NoActor: Int, @Header("Authorization") authHeader: String): Deferred<PersoActeurList>

    @GET("realisateurs/{NoReal}")
    fun getReal(@Path("NoReal") NoReal: Int, @Header("Authorization") authHeader: String): Deferred<PersoRealList>

    @GET("personnages/{NoPerson}")
    fun getPerson(@Path("NoPerson") NoPerson: Int, @Header("Authorization") authHeader: String): Deferred<PersoPersonList>

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

data class LoginInfo(
    @SerializedName("nomUtil") val nomUtil: String?,
    @SerializedName("motPasse") val motPasse: String?
)

data class loginRet(
    @Json(name="role") val role: String,
    @Json(name="token") val token: String,
)

data class PersoFilmList(
    @Json(name="noFilm") val noFilm: Int,
    @Json(name="titre") val titre: String,
    @Json(name="image") val image: String,
    @Json(name="duree") val duree: Int,
    @Json(name="dateSortie") val dateSortie: String,
    @Json(name="budget") val budget: Int,
    @Json(name="montantRecette") val montantRecette: Int,
    @Json(name="realisateur") val realisateur: Realisateur,
    @Json(name="categorie") val categorie: Categorie,
    @Json(name="personnages") val personnages: List<Personnage>,
)

data class PersoRealList(
    @Json(name="noRea") val noRea: Int,
    @Json(name="nomRea") val nomRea: String,
    @Json(name="prenRea") val prenRea: String,
    @Json(name="image") val image: String,
    @Json(name="films") val films: List<PersoFilmList>,
)

data class PersoPersonList(
    @Json(name="noPerso") val noPerso: Int,
    @Json(name="nomPers") val nomPers: String,
    @Json(name="film") val film: PersoFilmList,
    @Json(name="acteur") val acteur: Acteur,
)


data class PersoActeurList(
    @Json(name="noAct") val noAct: Int,
    @Json(name="nomAct") val nomAct: String,
    @Json(name="prenAct") val prenAct: String,
    @Json(name="dateNaiss") val dateNaiss: String,
    @Json(name="dateDeces") val dateDeces: String,
    @Json(name="image") val image: String,
    @Json(name="personnages") val personnages: List<Personnage>,
)
