package br.ufscar.mobile.meals.cenarios.meal_detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.ufscar.mobile.meals.R
import br.ufscar.mobile.meals.entidades.Ingredient
import br.ufscar.mobile.meals.entidades.Meal
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    companion object {
        private val ARG_MEAL =  "arg_meal"
        private val ARG_INGREDIENTS =  "arg_ingredientes"
        fun newInstance(meal: Meal, ingredients: ArrayList<Ingredient>) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MEAL, meal)
                    putSerializable(ARG_INGREDIENTS, ingredients)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val meal = getMeal()
        val ingredients = getIngredients()


        txt_title.text = meal.strMeal
        txt_instructions.text = meal.strInstructions

        activity?.let { that ->
            val adapter = IngredientAdapter(ingredients)
            val layoutManager = LinearLayoutManager(that)
            rvIngedients.adapter = adapter
            rvIngedients.layoutManager = layoutManager
        }

    }

    private fun getIngredients(): List<Ingredient> {
        val ingredients = arguments?.getSerializable(DetailsFragment.ARG_INGREDIENTS) as ArrayList<Ingredient>?
        if(ingredients == null) {
            throw NullPointerException("Ingredients can not be null")
        }
        return ingredients
    }

    fun getMeal(): Meal {
        val meal = arguments?.getSerializable(DetailsFragment.ARG_MEAL) as Meal?
        if(meal == null) {
            throw NullPointerException("Meal can not be null")
        }
        return meal
    }
}
