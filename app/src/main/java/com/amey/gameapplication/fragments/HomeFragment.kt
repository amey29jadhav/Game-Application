package com.amey.gameapplication.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.amey.gameapplication.adapters.GameAdapter
import com.amey.gameapplication.databinding.HomeFragmentBinding
import com.amey.gameapplication.viewmodel.HomeViewModel

class HomeFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var homeFragmentBinding: HomeFragmentBinding
    var gameAdapter: GameAdapter? = null

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        homeFragmentBinding.productsRecyclerView.layoutManager = LinearLayoutManager(context)

        homeFragmentBinding.searchview.setIconifiedByDefault(false)
        homeFragmentBinding.searchview.clearFocus()
        homeFragmentBinding.searchview.setOnQueryTextListener(this)


        return homeFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.armorList.observe(viewLifecycleOwner, Observer { armorlist ->
            if (viewModel.nameQueryLiveData.value!!.isEmpty()) {
                if (gameAdapter == null) {
                    gameAdapter = GameAdapter(viewModel.armorList.value!!)
                    homeFragmentBinding.productsRecyclerView.adapter = gameAdapter
                } else {
                    gameAdapter?.setValue(armorlist)
                    gameAdapter?.notifyDataSetChanged()
                }
            }
        })

        viewModel.getArmorsWithNameLiveData()?.observe(viewLifecycleOwner, Observer { armorlist ->
            gameAdapter?.setValue(armorlist)
            gameAdapter?.notifyDataSetChanged()
        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            /*val newText =viewModel.searchString.value +"$query"
            viewModel.searchString.value = newText*/
            viewModel.setNameQuery(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            /* val newText =viewModel.searchString.value +"$newText"
             viewModel.searchString.value = newText*/
            viewModel.setNameQuery(newText)
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}