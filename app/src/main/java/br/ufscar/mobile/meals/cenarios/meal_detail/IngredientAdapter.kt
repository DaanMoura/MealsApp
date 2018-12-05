package br.ufscar.mobile.meals.cenarios.meal_detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.ufscar.mobile.meals.R
import br.ufscar.mobile.meals.entidades.Ingredient
import kotlinx.android.synthetic.main.meal_ingedients.view.*

class IngredientAdapter(val ingredients: List<Ingredient>) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_ingedients, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient: Ingredient = ingredients[position]
        holder.bindView(ingredient)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun bindView(ingredient: Ingredient) {
            if(!ingredient.measure.isBlank()) {
                itemView.txt_measure.text = ingredient.measure
                itemView.txt_ingredient.text = ingredient.ingredient
            }
        }

    }

}