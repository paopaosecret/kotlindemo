package com.example.kotlindemo.jetpack.adapter

import android.content.Context
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.jetpack.entity.Word

class WordAdapter() : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    constructor(context: Context) : this() {
        mInflater = LayoutInflater.from(context)
    }

    class WordViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView
        val tvDesc: TextView

        init {
            tvWord = itemView.findViewById(R.id.tv_word)
            tvDesc = itemView.findViewById(R.id.tv_desc)
        }
    }

    private var mInflater: LayoutInflater? = null
    private var mWords: List<Word>? = null

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView =
            mInflater!!.inflate(R.layout.adapter_jetpack_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (mWords != null) {
            mWords!!.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (mWords != null) {
            val current = mWords!![position]
            holder.tvWord.text = "word:" + current.word
            holder.tvDesc.text = "desc:" + current.description
        } else {
            // Covers the case of data not being ready yet.
            holder.tvWord.text = "No Word"
            holder.tvWord.text = "No Description"
        }
    }

    fun setWords(words: List<Word>?) {
        mWords = words
        notifyDataSetChanged()
    }
}