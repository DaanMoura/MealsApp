package br.ufscar.mobile.meals.network

import br.ufscar.mobile.meals.entidades.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {


    @GET("latest.php")
    fun getLatest(): Call<MealList>

    @GET("search.php")
    fun getSearch(@Query("s") s: String): Call<MealList>

    @GET("random.php")
    fun getRandom(): Call<MealList>

}