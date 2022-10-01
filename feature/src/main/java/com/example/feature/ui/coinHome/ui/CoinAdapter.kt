/*
 * Copyright 2022 AHMED ABDELHAK. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.feature.ui.coinHome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.entity.Coin
import com.example.feature.databinding.ItemCoinBinding

/**
 * Adapter class for RecyclerView
 */
class CoinAdapter constructor(
    private val result: List<Coin>,
    private val clickFunc : ((Coin) -> Unit)? = null
) : RecyclerView.Adapter<CoinViewHolder>() {

    val data:MutableList<Coin> = result.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return CoinViewHolder(binding = binding, click = clickFunc)

    }

    fun appendList(list:List<Coin>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.doBindings((data[position]))
        holder.bind()
    }

    override fun getItemCount(): Int {
        return data.size
    }


}

class CoinItemDiffUtil : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }
}