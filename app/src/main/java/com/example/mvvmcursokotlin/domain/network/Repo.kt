package com.example.mvvmcursokotlin.domain.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmcursokotlin.Usuario
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

class Repo() {
    
    //fun getUserData(context: Context): LiveData<MutableList<Usuario>>{
    fun getUserData(): LiveData<MutableList<Usuario>>{
        val mutableData = MutableLiveData<MutableList<Usuario>>()
        //FirebaseApp.initializeApp(context)
        FirebaseFirestore.getInstance().collection("Usuarios")
            .get().addOnSuccessListener { result ->

            val listData = mutableListOf<Usuario>()

            for(document in result){
                val imgUrl = document.getString("imageUrl")
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")
                val usuario = Usuario(imgUrl!!,nombre!!,descripcion!!)
                listData.add(usuario)
            }
                mutableData.value = listData
        }
        return mutableData

    }


    fun getUserDataDataBase(context: Context): LiveData<MutableList<Usuario>>{
        val mutableData = MutableLiveData<MutableList<Usuario>>()
        FirebaseApp.initializeApp(context)

        val databaseReference = FirebaseDatabase.getInstance().getReference()

        databaseReference.child("AppBitacoras")
            .child("Usuario")
        val eventListenerDepartamentos: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val listData = mutableListOf<Usuario>()

                for (document in dataSnapshot.children) {
                    val imgUrl = document.child("imageUrl")
                    val nombre = document.child("nombre")
                    val descripcion = document.child("descripcion")
                    val usuario = Usuario(imgUrl.toString()!!, nombre.toString()!!,descripcion.toString()!!)
                    listData.add(usuario)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("DatosMedidores:", "DSVALUEError:" + databaseError.message)
            }
        }

        databaseReference.addListenerForSingleValueEvent(eventListenerDepartamentos)
        return mutableData
    }

}