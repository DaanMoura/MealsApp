package br.ufscar.mobile.meals.cenarios.meal_list


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.ufscar.mobile.meals.R
import br.ufscar.mobile.meals.entidades.Meal
import br.ufscar.mobile.meals.entidades.MealList
import com.google.android.youtube.player.YouTubePlayerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    companion object {
        private val ARG_LIST =  "arg_list"
        fun newInstance(list: ArrayList<Meal>) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_LIST, list)
                }
            }
    }

    var listener: onFragmentInteractionListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mealList = getMealList()
        activity?.let { that ->
            val adapter = MealAdapter(mealList, that)
            adapter.setClickListener { index ->
                listener?.onMealInteraction(mealList.get(index))
            }

            val layoutManager = LinearLayoutManager(that)
            rvMeal.adapter = adapter
            rvMeal.layoutManager = layoutManager
        }

        btn_random.setOnClickListener {
            listener?.onRandomInteraction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is MainFragment.onFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException (
                context.toString() + "must implement MainFragment.onFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun getMealList(): ArrayList<Meal> {
        val mealList = arguments?.getSerializable(ARG_LIST) as ArrayList<Meal>?
        if(mealList == null) {
            throw NullPointerException("Meal List can not be null")
        }
        return mealList
    }

    interface onFragmentInteractionListener {
        fun onMealInteraction(meal: Meal)
        fun onRandomInteraction()
    }


}
