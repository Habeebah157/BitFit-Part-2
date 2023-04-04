package com.example.wishlist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [listFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class listFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val nutritionlists= mutableListOf<Fitbitentity>()
    private lateinit var nutritionRv: RecyclerView
    private lateinit var nutritionAdapter: NutritionAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list,container,false)

        nutritionRv = view.findViewById<RecyclerView>(R.id.article_recycler_view)
        nutritionAdapter = NutritionAdapter(requireContext(),nutritionlists)
        nutritionRv.adapter = nutritionAdapter

        nutritionRv.layoutManager = LinearLayoutManager(requireContext()).also {
            val dividerItemDecoration = DividerItemDecoration(requireContext(), it.orientation)
            nutritionRv.addItemDecoration(dividerItemDecoration)
        }
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {

            (requireActivity().application as MyApplication).db.FitBitDao().getAll().collect { databaseList ->
                nutritionlists.clear()
                databaseList.map { mappedList ->
                    nutritionlists.addAll(listOf(mappedList))
                    nutritionAdapter.notifyDataSetChanged()
                }
            }

        }

        nutritionRv.layoutManager = LinearLayoutManager(requireContext()).also {
            val dividerItemDecoration = DividerItemDecoration(requireContext(), it.orientation)
            nutritionRv.addItemDecoration(dividerItemDecoration)
        }

        val add1 = view.findViewById<Button>(R.id.button5)
        add1.setOnClickListener{
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            startActivity(intent)
        }
//        nutritionAdapter.setOnItemClickListener(object: NutritionAdapter.OnItemClickListener{
//            override fun onItemClick(position: Int){
//                Toast.makeText(requireContext(),"Item removed at position $position", Toast.LENGTH_LONG).show()
//                val itemToDelete = nutritionlists[position]
//                lifecycleScope.launch(Dispatchers.IO){
//                    (requireActivity().application as MyApplication).db.FitBitDao().deleteAll()
//                }
//                nutritionlists.removeAt(position)
//                nutritionAdapter.notifyItemRemoved(position)
//            }
//        })



    }
}