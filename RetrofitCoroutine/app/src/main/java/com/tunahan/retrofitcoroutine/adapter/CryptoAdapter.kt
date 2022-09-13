package com.tunahan.retrofitcoroutine.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.retrofitcoroutine.R
import com.tunahan.retrofitcoroutine.model.CryptoModel
import kotlinx.android.synthetic.main.recycler_row.view.*

class CryptoAdapter(private val cryptoList: ArrayList<CryptoModel>, private val listener: Listener) : RecyclerView.Adapter<CryptoAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    private val colors:Array<String> = arrayOf("#2a22a2","#876a7c","#ff8484","#0dbdb2","#cc0051")


    class RowHolder(view: View):RecyclerView.ViewHolder(view) {

        fun bind(cryptoModel: CryptoModel,colors:Array<String>,position: Int,listener:Listener){


            itemView.setOnClickListener{
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position%5]))
            itemView.text_name.text = cryptoModel.currency
            itemView.text_price.text = cryptoModel.price



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(cryptoList[position],colors,position,listener)

    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}