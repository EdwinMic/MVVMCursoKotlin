package com.example.mvvmcursokotlin

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter(private val context:Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Usuario>()
    fun setListData(data:MutableList<Usuario>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.e("dataList",""+dataList.size)
        return if(dataList.size > 0){
            dataList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = dataList[position]
        holder.bindView(user)
    }

    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(user:Usuario){
            Glide.with(context).load(user.imageUrl).into(itemView.imgCircle)
            itemView.txtTitulo.text = user.nombre
            itemView.txtDescripcion.text = user.descripcion
        }
    }


}