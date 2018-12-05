package br.ufscar.mobile.meals.cenarios.meal_list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.Toast
import br.ufscar.mobile.meals.R
import br.ufscar.mobile.meals.cenarios.meal_detail.DetailsFragment
import br.ufscar.mobile.meals.entidades.Ingredient
import br.ufscar.mobile.meals.entidades.Meal
import br.ufscar.mobile.meals.fragments.MyYoutubeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View,
    MainFragment.onFragmentInteractionListener, DetailsFragment.onFragmentInteractionListener {

    val presenter: MainContract.Presenter = MainPresenter(this)
    var wasSearched: Boolean = false //Essa variável é útil para o controle de navegação

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onUpdateList()
    }

    // Essa função exibe o fragment da lista das receitas, é executada após o presenter carregar a lista
    override fun showList(list: List<Meal>) {
        val fragment = MainFragment.newInstance(list as ArrayList<Meal>)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fmMaster, fragment)
            .commit()
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

    //Abrir site no navegador
    override fun onButtonInteraction(site: String) {
        val webpage: Uri = Uri.parse(site)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            showMessage("There is no url")
        }
    }


    override fun onMealInteraction(meal: Meal) {
        showMeal(meal)
    }

    override fun showRandom(meal: Meal) {
        showMeal(meal)
    }

    override fun onRandomInteraction() {
        presenter.onGetRandom()
    }

    //Essa função exibe o fragments de detalhes
    fun showMeal(meal: Meal) {
        wasSearched = false
        val ingredients = getIngredients(meal)
        val fragmentDetail = DetailsFragment.newInstance(meal, ingredients as ArrayList<Ingredient>)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fmMaster, fragmentDetail)
            .addToBackStack(null)
            .commit()
    }

    //Essa função coloca o fragment youtube na tela de detalhes
    override fun showYouTubePlayer(meal: Meal) {
        val videoID = meal.strYoutube!!.substringAfter("watch?v=")
        val youtubeFragment = MyYoutubeFragment.newInstance(videoID)

        supportFragmentManager.beginTransaction()
            .replace(R.id.youtubeplayerfragment, youtubeFragment)
            .commit()
    }

    //Retorna a lista com todos os ingredientes e medidas da receita.
    //Foi realizado dessa forma pois os ingredientes na api estão como elementos separados
    private fun getIngredients(meal: Meal): List<Ingredient> {
        val ingredients: ArrayList<Ingredient> = ArrayList()
        if (!meal.strIngredient1.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure1, meal.strIngredient1))
        }
        if (!meal.strIngredient2.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure2, meal.strIngredient2))
        }
        if (!meal.strIngredient3.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure3, meal.strIngredient3))
        }
        if (!meal.strIngredient4.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure4, meal.strIngredient4))
        }
        if (!meal.strIngredient5.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure5, meal.strIngredient5))
        }
        if (!meal.strIngredient6.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure6, meal.strIngredient6))
        }
        if (!meal.strIngredient7.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure7, meal.strIngredient7))
        }
        if (!meal.strIngredient8.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure8, meal.strIngredient8))
        }
        if (!meal.strIngredient9.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure9, meal.strIngredient9))
        }
        if (!meal.strIngredient10.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure10, meal.strIngredient10))
        }
        if (!meal.strIngredient11.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure11, meal.strIngredient11))
        }
        if (!meal.strIngredient12.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure12, meal.strIngredient12))
        }
        if (!meal.strIngredient13.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure13, meal.strIngredient13))
        }
        if (!meal.strIngredient14.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure14, meal.strIngredient14))
        }
        if (!meal.strIngredient15.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure15, meal.strIngredient15))
        }
        if (!meal.strIngredient16.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure16, meal.strIngredient16))
        }
        if (!meal.strIngredient17.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure17, meal.strIngredient17))
        }
        if (!meal.strIngredient18.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure18, meal.strIngredient18))
        }
        if (!meal.strIngredient19.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure19, meal.strIngredient19))
        }
        if (!meal.strIngredient20.isNullOrEmpty()) {
            ingredients.add(Ingredient(meal.strMeasure20, meal.strIngredient20))
        }
        return ingredients
    }

    override fun onBackPressed() {
        if(wasSearched) {
            wasSearched = false
            presenter.onUpdateList()
        }
        else
            super.onBackPressed()
    }

    //Campo de busca
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)

        val searchItem = menu.findItem(R.id.search_bar)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search for a meal!"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                wasSearched = true
                presenter.onSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


        return true
    }


}
