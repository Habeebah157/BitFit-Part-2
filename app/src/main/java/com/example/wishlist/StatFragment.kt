package com.example.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [StatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stat,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val max = activity?.findViewById(R.id.maxcaloriesval) as TextView
        val min = activity?.findViewById(R.id.mincaloriesval) as TextView
        val avg = activity?.findViewById(R.id.avgcaloriesval) as TextView

        var smallesInt: Int = Byte.MAX_VALUE.toInt()
        var maxVal: Int = Byte.MIN_VALUE.toInt()
        var numOfCal: Int = 0
        var sumOfCal: Int = 0
        lifecycleScope.launch(Dispatchers.IO){
            for(item in (activity?.application as MyApplication).db.FitBitDao().getCal()){
                if(isValidInt(item)){
                    numOfCal += 1
                    sumOfCal += Integer.parseInt(item)
                    avg.text = "Average Int: " + (sumOfCal/numOfCal).toString()
                    if (Integer.parseInt(item)>maxVal){
                        maxVal = Integer.parseInt(item)
                    }
                    if(Integer.parseInt(item)<smallesInt){
                        smallesInt = Integer.parseInt(item)
                    }
                    max.text = "Largest Int: "+maxVal.toString()
                    min.text = "Smallest Int: " +smallesInt.toString()
                }


            }
        }



    }
fun isValidInt(num:String):Boolean{
    return num.toIntOrNull() != null
}
}