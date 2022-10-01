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

import android.widget.ImageView
import com.example.entity.Coin
import com.example.feature.R
import com.example.feature.core.BaseViewHolder
import com.example.feature.databinding.ItemCoinBinding
import com.example.feature.extension.loadImagesWithCoil
import com.example.feature.extension.loadImagesWithGlide

/**
 * ViewHolder class for Item
 */
class CoinViewHolder constructor(
    private val binding : ItemCoinBinding,
    private val click : ((Coin) -> Unit)? = null
) : BaseViewHolder<Coin, ItemCoinBinding>(binding) {


    init {
        binding.root.setOnClickListener {
            click?.invoke(getRowItem()!!)
        }
    }

    override fun bind() {


        getRowItem()?.let {

            binding.tvName.text = it.name
            binding.tvCurrentPrice.text = "$" + it.current_price.toString()
            binding.tvPercentagePercentage.text = it.price_change_percentage_24h
            it.image?.let { it1 -> binding.imageView.loadImagesWithCoil(it1) }
            it.price_change_percentage_24h?.let { it1 -> setIncreaseDecrease(binding.imgIncreasDec, it1.toDouble()) }

            binding.executePendingBindings()

        }
    }

    private fun setIncreaseDecrease(imageView: ImageView, price: Double) {
        if (price.toString().contains("-")) imageView.setImageResource(R.drawable.ic_decrease)
        else imageView.setImageResource(R.drawable.ic_increase)
    }
}