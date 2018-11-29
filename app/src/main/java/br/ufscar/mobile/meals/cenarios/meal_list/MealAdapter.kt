package br.ufscar.mobile.meals.cenarios.meal_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.ufscar.mobile.meals.R
import br.ufscar.mobile.meals.entidades.Meal
import kotlinx.android.synthetic.main.meal_item.view.*

class MealAdapter(val meals:ArrayList<Meal>) : RecyclerView.Adapter<MealAdapter.ViewHolder>() {
    var onClickListener: ((index: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal: Meal = meals[position]
        holder.bindView(meal ,onClickListener)
    }

    fun setClickListener(clique: ((index: Int) -> Unit)) {
        this.onClickListener = clique
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun bindView(meal: Meal,
                     click: ((index: Int) -> Unit)?) {
            itemView.txt_title_item.text = meal.strMeal
            itemView.txt_category_item.text = meal.strCategory

            if(click != null) {
                itemView.setOnClickListener {
                    click.invoke(adapterPosition)
                }
            }
        }

    }

}