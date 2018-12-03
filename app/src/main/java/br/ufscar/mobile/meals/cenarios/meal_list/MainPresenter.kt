package br.ufscar.mobile.meals.cenarios.meal_list

import br.ufscar.mobile.meals.entidades.MealList
import br.ufscar.mobile.meals.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {
    override fun onSearch(query: String?) {
        val mealService = RetrofitInitializer().createMealService()
        if(query != null) {
            val call = mealService.getSearch(query)

            view.showLoading()

            call.enqueue(object : Callback<MealList> {
                override fun onFailure(call: Call<MealList>, t: Throwable) {
                    view.showMessage("Connection failed. Check your internet access")
                    view.hideLoading()
                }

                override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                    if(response.body() != null) {
                        if(response.body()?.meals != null)
                            view.showList(response.body()!!.meals)
                        else
                            view.showMessage("Nothing to show")
                    } else {
                        view.showMessage("Nothing to show")
                    }
                    view.hideLoading()
                }
            })
        } else {
            view.showMessage("Search is null")
        }
    }

    override fun onGetRandom() {
        val mealService = RetrofitInitializer().createMealService()
        val call = mealService.getRandom()

        view.showLoading()

        call.enqueue(object : Callback<MealList> {
            override fun onFailure(call: Call<MealList>, t: Throwable) {
                view.showMessage("Connection failed. Check your internet access")
                view.hideLoading()
            }

            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() != null) {
                    view.showRandom(response.body()!!.meals[0])
                } else {
                    view.showMessage("Nothing to show")
                }
                view.hideLoading()
            }
        })
    }

    override fun onUpdateList() {
        val mealService = RetrofitInitializer().createMealService()
        val call = mealService.getLatest()

        view.showLoading()

        call.enqueue(object : Callback<MealList> {
            override fun onFailure(call: Call<MealList>, t: Throwable) {
                view.showMessage("Connection failed. Check your internet access")
                view.hideLoading()
            }

            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() != null) {
                    view.showList(response.body()!!.meals)
                } else {
                    view.showMessage("Nothing to show")
                }
                view.hideLoading()
            }
        })
    }
}