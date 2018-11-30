package br.ufscar.mobile.meals.cenarios.meal_list

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import br.ufscar.mobile.meals.R
import br.ufscar.mobile.meals.entidades.Meal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    val presenter: MainContract.Presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun showList(list: List<Meal>) {
        val adapter = MealAdapter(list, this)
        val layoutManager = LinearLayoutManager(this)

        rvMeal.adapter = adapter
        rvMeal.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        presenter.onUpdateList()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }



//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_recent -> {
//                return@OnNavigationItemSelectedListener true
//            }
//
//            R.id.navigation_random -> {
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }
}
