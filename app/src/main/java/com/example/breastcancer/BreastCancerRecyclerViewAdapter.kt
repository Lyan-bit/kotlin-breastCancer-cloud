package com.example.breastcancer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class BreastCancerRecyclerViewAdapter (items: List<BreastCancerVO>, listener: ListBreastCancerFragment.OnListBreastCancerFragmentInteractionListener)
    : RecyclerView.Adapter<BreastCancerRecyclerViewAdapter.BreastCancerViewHolder>() {

    private var mValues: List<BreastCancerVO> = items
    private var mListener: ListBreastCancerFragment.OnListBreastCancerFragmentInteractionListener = listener
	
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreastCancerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewlistbreastcancer_item, parent, false)
        return BreastCancerViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BreastCancerViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.listBreastCancerIdView.text = " " + mValues[position].id + " "
        holder.listBreastCancerAgeView.text = " " + mValues[position].age + " "
        holder.listBreastCancerBmiView.text = " " + mValues[position].bmi + " "
        holder.listBreastCancerGlucoseView.text = " " + mValues[position].glucose + " "
        holder.listBreastCancerInsulinView.text = " " + mValues[position].insulin + " "
        holder.listBreastCancerHomaView.text = " " + mValues[position].homa + " "
        holder.listBreastCancerLeptinView.text = " " + mValues[position].leptin + " "
        holder.listBreastCancerAdiponectinView.text = " " + mValues[position].adiponectin + " "
        holder.listBreastCancerResistinView.text = " " + mValues[position].resistin + " "
        holder.listBreastCancerMcpView.text = " " + mValues[position].mcp + " "
        holder.listBreastCancerOutcomeView.text = " " + mValues[position].outcome + " "

        holder.mView.setOnClickListener { mListener.onListBreastCancerFragmentInteraction(holder.mItem) }
    }
    
    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class BreastCancerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var mView: View
        var listBreastCancerIdView: TextView
        var listBreastCancerAgeView: TextView
        var listBreastCancerBmiView: TextView
        var listBreastCancerGlucoseView: TextView
        var listBreastCancerInsulinView: TextView
        var listBreastCancerHomaView: TextView
        var listBreastCancerLeptinView: TextView
        var listBreastCancerAdiponectinView: TextView
        var listBreastCancerResistinView: TextView
        var listBreastCancerMcpView: TextView
        var listBreastCancerOutcomeView: TextView
        lateinit var mItem: BreastCancerVO

        init {
            mView = view
            listBreastCancerIdView = view.findViewById(R.id.listBreastCancerIdView)
            listBreastCancerAgeView = view.findViewById(R.id.listBreastCancerAgeView)
            listBreastCancerBmiView = view.findViewById(R.id.listBreastCancerBmiView)
            listBreastCancerGlucoseView = view.findViewById(R.id.listBreastCancerGlucoseView)
            listBreastCancerInsulinView = view.findViewById(R.id.listBreastCancerInsulinView)
            listBreastCancerHomaView = view.findViewById(R.id.listBreastCancerHomaView)
            listBreastCancerLeptinView = view.findViewById(R.id.listBreastCancerLeptinView)
            listBreastCancerAdiponectinView = view.findViewById(R.id.listBreastCancerAdiponectinView)
            listBreastCancerResistinView = view.findViewById(R.id.listBreastCancerResistinView)
            listBreastCancerMcpView = view.findViewById(R.id.listBreastCancerMcpView)
            listBreastCancerOutcomeView = view.findViewById(R.id.listBreastCancerOutcomeView)
        }

        override fun toString(): String {
            return super.toString() + " " + mItem
        }

    }
}
