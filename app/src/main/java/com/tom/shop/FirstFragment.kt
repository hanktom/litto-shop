package com.tom.shop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.room.Room
import com.tom.shop.databinding.FragmentFirstBinding
import com.tom.shop.db.ProductDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val TAG = FirstFragment::class.java.simpleName
    lateinit var cities : Array<String>
    private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cities = requireContext().resources.getStringArray(R.array.cities)
        //Products from web url
        /*thread {
            val json = URL("https://fakestoreapi.com/products").readText()
            Log.d(TAG, "onViewCreated: JSON $json")
        }*/
        //RecyclerView
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.setHasFixedSize(true)
        viewModel.products.observe(viewLifecycleOwner) {
            binding.recycler.adapter = ProductAdapter(it)
            val db = Room.databaseBuilder(requireContext(),
                ProductDatabase::class.java, "shop").build()
            lifecycleScope.launch(Dispatchers.IO) {
                db.productDao().insert(it[0])
            }
        }

        /*binding.recycler.adapter = object : RecyclerView.Adapter<CityViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.row_city, parent, false)
                return CityViewHolder(view)
            }

            override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
                holder.cityName.text = cities[position]
            }

            override fun getItemCount(): Int {
                return cities.size
            }

        }*/


        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class CityAdapter : Adapter<CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cityName: TextView = itemView.findViewById(R.id.city_name)
}
