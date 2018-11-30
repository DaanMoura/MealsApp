package br.ufscar.mobile.meals.cenarios.meal_list

import br.ufscar.mobile.meals.entidades.Meal

interface MainContract {
    interface View {
        fun showList(list: List<Meal>)
        fun showMessage(message: String)
        fun hideLoading()
        fun showLoading()
        fun showRandom(meal: Meal)
    }

    interface Presenter {
        fun onUpdateList()
        fun onGetRandom()
        fun onSearch(query: String?)
    }
}