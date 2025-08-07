package com.fns.examtodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RationAdapter : RecyclerView.Adapter<RationAdapter.RationViewHolder>() {

    private var rationList = listOf<RationCardTB>()

    class RationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.nameText)
        val aadhaar = itemView.findViewById<TextView>(R.id.aadhaarText)
        val category = itemView.findViewById<TextView>(R.id.categoryText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ration, parent, false)
        return RationViewHolder(view)
    }

    override fun onBindViewHolder(holder: RationViewHolder, position: Int) {
        val ration = rationList[position]
        holder.name.text = ration.applicantName
        holder.aadhaar.text = ration.aadhaarNumber
        holder.category.text = ration.Category
    }

    override fun getItemCount() = rationList.size

    fun setData(data: List<RationCardTB>) {
        rationList = data
        notifyDataSetChanged()
    }
}
