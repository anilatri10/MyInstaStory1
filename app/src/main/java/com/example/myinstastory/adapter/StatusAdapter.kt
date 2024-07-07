package com.example.myinstastory.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myinstastory.R
import com.example.myinstastory.activity.StatusActivity

import com.example.myinstastory.data.InstaStatus

class StatusAdapter(val activity: Context, val statusList: ArrayList<InstaStatus>) : RecyclerView.Adapter<StatusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_status_layout, p0, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return statusList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        if (p1 == 0) {
            p0.add.visibility = View.VISIBLE
        } else{
            p0.add.visibility = View.INVISIBLE

        }

        p0.name?.text = statusList[p1].name
        p0.name.visibility = View.VISIBLE

        Glide.with(activity)
            .load(statusList[p1].imageUrl)
            .into(p0.photo)

        p0.itemView.setOnClickListener {
//            Toast.makeText(activity,"click me", Toast.LENGTH_LONG).show();
            val intent_status = Intent(activity, StatusActivity::class.java)
            intent_status.putExtra("id",statusList[p1].id)
            intent_status.putExtra("name",statusList[p1].name)
            activity.startActivity(intent_status)
        }

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.profile_name)
        val photo = itemView.findViewById<ImageView>(R.id.profile_img)
        val add = itemView.findViewById<ImageView>(R.id.ic_add_img)
    }
}