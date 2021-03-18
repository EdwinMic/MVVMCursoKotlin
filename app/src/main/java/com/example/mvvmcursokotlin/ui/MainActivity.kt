package com.example.mvvmcursokotlin.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcursokotlin.MainAdapter
import com.example.mvvmcursokotlin.R
import com.example.mvvmcursokotlin.viewmodel.MainViewModel
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        observerData()


        /*val dummyList:MutableList<Usuario> = mutableListOf<Usuario>()
        dummyList.add(Usuario("https://meridiano.mx/files/2020/05/21/1590117059_santos.jpg",
                              "Santos Laguna",
                               "Un Guerrero Nunca Muere"
        ))

        dummyList.add(Usuario("https://meridiano.mx/files/2020/05/21/1590117059_santos.jpg",
            "Santos Laguna",
            "Un Guerrero Nunca Muere"
        ))

        dummyList.add(Usuario("https://meridiano.mx/files/2020/05/21/1590117059_santos.jpg",
            "Santos Laguna",
            "Un Guerrero Nunca Muere"
        ))



        adapter.setListData(dummyList)
        adapter.notifyDataSetChanged()*/

    }

    fun observerData() {
        viewModel.fetchUserData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })

    }
}