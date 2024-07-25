package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.ContactItemBinding
import com.example.myapplication.model.ContactModel

class ContactAdapter(var contactList: MutableList<ContactModel>?=null): Adapter<ContactAdapter.ContactViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemBinding=ContactItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return contactList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item= contactList!![position]
        holder.itemBinding.nameTv.text=item.name
        holder.itemBinding.phoneNumberTv.text=item.phone

        holder.itemBinding.dragItem.setOnClickListener{
            onItemClickListener?.onItemClick(position,item)
        }

        holder.itemBinding.deleteView.setOnClickListener{
            onItemDeleteListener?.onItemClick(position,item)
            notifyDataSetChanged()
        }
        holder.itemBinding.icCall.setOnClickListener{
            onItemCallListener?.onItemClick(position,item)
        }

    }

    fun refreshList(copyContactList: MutableList<ContactModel>) {
        this.contactList=copyContactList
        notifyDataSetChanged()
    }


    class ContactViewHolder(var itemBinding:ContactItemBinding):ViewHolder(itemBinding.root){

    }
    var onItemCallListener:OnItemClickListener?=null
    var onItemDeleteListener:OnItemClickListener?=null
    var onItemClickListener:OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(position:Int, contact: ContactModel)

    }


}


/*
*     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        var itemBinding=ContactItemBinding.inflate(LayoutInflater.from(parent.context))
        return ContactViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return   contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var item=contactList[position]
        holder.username.text=item.name
        holder.phone.text=item.phone
        //holder.description.text=item.description

//        if(onItemClickListener!=null){
//            holder.itemView.setOnClickListener{
//                onItemClickListener?.onItemClick(position, item)
//            }

        }
* */
