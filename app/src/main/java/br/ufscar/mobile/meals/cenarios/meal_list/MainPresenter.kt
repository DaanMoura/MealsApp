package br.ufscar.mobile.meals.cenarios.meal_list

import br.ufscar.mobile.meals.entidades.MealList
import br.ufscar.mobile.meals.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {
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