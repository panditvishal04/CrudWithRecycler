package com.vishal.crudwithrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerData(var studentArray : ArrayList<EntityData>, var ItemClick : ItemClick) : RecyclerView.Adapter<RecyclerData.ViewHolder>(){
    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        var tvName =  view.findViewById<TextView>(R.id.tvName)
        var btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
        var btnDelete =  view.findViewById<Button>(R.id.btnDelete)
        var tvId = view.findViewById<TextView>(R.id.tvId)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter,parent,false))
    }

    override fun getItemCount(): Int  = studentArray.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvName.setText(studentArray[position].name)
        holder.tvId.setText(studentArray[position].id.toString())

        holder.btnUpdate.setOnClickListener {
            ItemClick.updateItem(studentArray[position].id)
        }

        holder.btnDelete.setOnClickListener {
            ItemClick.deleteItem(position)
        }
    }


}