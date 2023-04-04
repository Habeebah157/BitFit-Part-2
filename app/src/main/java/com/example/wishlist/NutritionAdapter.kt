package com.example.wishlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NutritionAdapter(private val context: Context, private val nutritionList: List<Fitbitentity>) :

RecyclerView.Adapter<NutritionAdapter.ViewHolder>(){
//    private lateinit var mListner: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
//        mListner = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
//        return ViewHolder(view, mListner)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nutritionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = nutritionList[position]
        holder.bind(food)
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val food = itemView.findViewById<TextView>(R.id.fooditem)
        private val calories = itemView.findViewById<TextView>(R.id.caloricitem)

        init {
            itemView.setOnLongClickListener {
                true
            }
        }

        fun bind(nutrition: Fitbitentity) {

            food.text = nutrition.food
            calories.text = nutrition.calories
        }



    }


}



