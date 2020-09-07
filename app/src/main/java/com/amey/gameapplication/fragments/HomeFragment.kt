package com.amey.gameapplication.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amey.gameapplication.R
import com.amey.gameapplication.adapters.GameAdapter
import com.amey.gameapplication.databinding.HomeFragmentBinding
import com.amey.gameapplication.utils.SpacesItemDecoration
import com.amey.gameapplication.viewmodel.HomeViewModel

class HomeFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var homeFragmentBinding: HomeFragmentBinding
    var gameAdapter: GameAdapter? = null
    lateinit var myContext: Context
    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var progressText: TextView
    private lateinit var productsRecyclerView: RecyclerView


    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        initUI()
        return homeFragmentBinding.root
    }

    /***
     *  Method to initialize views
     */
    private fun initUI() {

        progressText = homeFragmentBinding.progressText
        progressBar = homeFragmentBinding.progressBar
        searchView = homeFragmentBinding.searchview
        productsRecyclerView = homeFragmentBinding.productsRecyclerView

        productsRecyclerView.layoutManager = LinearLayoutManager(context)
        productsRecyclerView.addItemDecoration(
            SpacesItemDecoration(
                5,
                ContextCompat.getColor(myContext, R.color.separator_color),
                0.5f,
                myContext
            )
        )

        searchView.setIconifiedByDefault(false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        /***
         *  observer to receive filtered list on performing search by armor name
         */
        viewModel.getArmorsWithNameLiveData()?.observe(viewLifecycleOwner, Observer { armorlist ->

            gameAdapter = GameAdapter(armorlist)
            productsRecyclerView.adapter = gameAdapter

        })


        /***
         * Observer to show/hide progress bar & search view
         */
        viewModel.showProgressBar.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
                progressText.visibility = View.VISIBLE
                searchView.visibility = View.GONE

            } else {
                progressBar.visibility = View.GONE
                progressText.visibility = View.GONE
                searchView.visibility = View.VISIBLE
                searchView.setOnQueryTextListener(this)


            }
        })


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            viewModel.setNameQuery(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            viewModel.setNameQuery(newText)
        }
        return false
    }

}