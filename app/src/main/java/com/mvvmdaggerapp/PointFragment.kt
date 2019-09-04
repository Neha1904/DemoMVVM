package com.mvvmdaggerapp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvmdaggerapp.model.Point
import com.mvvmdaggerapp.viewmodels.PointViewModel
import kotlinx.android.synthetic.main.point_fragment.*
import javax.inject.Inject


class PointFragment : Fragment() {

    companion object {
        fun newInstance() = PointFragment()
    }

    @Inject
    lateinit var factory : ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.point_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DaggerPointComponent.create().inject(this)
        val viewModel = ViewModelProviders.of(this, factory).get(PointViewModel::class.java)

        viewModel.getLiveData().observe(this, Observer { points ->
            val pointAdapter = context?.let { PointAdapter(points as ArrayList<Point>, it) }
            recyclerView.adapter = pointAdapter
        })
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

}
