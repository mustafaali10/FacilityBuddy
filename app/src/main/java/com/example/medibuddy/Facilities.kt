package com.example.medibuddy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Facilities : Fragment() {

        private var mList=ArrayList<ItemsData>()
        private lateinit var adapter: ItemsAdapter
        private var originalList = ArrayList<ItemsData>()
        private lateinit var progressBar:ProgressBar

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            originalList.addAll(mList)


            val searchView = (activity as MainActivity).searchView
            //progressBar=view.findViewById<ProgressBar>(R.id.progressBar)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { searchFacilities(it) }
                    return true
                }
            })

            searchView.setOnCloseListener {
                searchFacilities("")
                true
            }
        }

        private fun searchFacilities(query: String) {
            val filteredList = if(query.isEmpty()){
                originalList
            }
            else{
                originalList.filter { item ->
                    item.title.toLowerCase().contains(query.toLowerCase())}
            }
            adapter.updateData(filteredList)
        }


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment

            val view=inflater.inflate(R.layout.fragment_facilities, container, false)

            val recyclerView: RecyclerView =view.findViewById(R.id.recyclerView) //POSSIBLE ERROR HERE

            recyclerView.setHasFixedSize(true)
            adapter= ItemsAdapter(mList)
            recyclerView.layoutManager= GridLayoutManager(context,2) //POSSIBLE ERROR HERE ALSO

            addDataToList()



            recyclerView.adapter=adapter

            adapter.setOnItemClickListener(object : ItemsAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {

                    //progressBar.visibility=View.VISIBLE
                    val intent= Intent(context, DetailsActivity::class.java)


                    intent.putExtra("title",mList[position].title)
                    intent.putExtra("logo",mList[position].logo)
                    intent.putExtra("price",mList[position].price)
                    startActivity(intent)

                    Toast.makeText(context,"Item $position clicked", Toast.LENGTH_SHORT).show()
                }

            })

            return view
        }

        private fun addDataToList(){

            mList.add(ItemsData("M.R.I", R.drawable.mrimachine,"₹ 5200"))
            mList.add(ItemsData("X-Ray", R.drawable.xray,"₹ 800"))
            mList.add(ItemsData("Blood Test", R.drawable.bloodtest,"₹ 500"))
            mList.add(ItemsData("C.T Scan", R.drawable.ctscan,"₹ 4000"))
            mList.add(ItemsData("Ultrasound", R.drawable.ultrasound,"₹ 5400"))


        }

    }