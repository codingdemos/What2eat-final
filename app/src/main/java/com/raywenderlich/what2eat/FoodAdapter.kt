/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.what2eat

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.recyclerview_food_row.view.*

class FoodAdapter(val mContext: Context, val mFoodTitle: Array<String>, val mFoodImages: IntArray) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
    val layout = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_food_row, parent,
        false)
    return FoodViewHolder(layout)
  }

  override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
    holder.mTitle.text = mFoodTitle[position]
    holder.mImage.setImageResource(mFoodImages[position])
    holder.mLayout.setOnClickListener(View.OnClickListener { view ->
      val mIntent = Intent(mContext,FoodDetailActivity::class.java)
      mIntent.putExtra("title", mFoodTitle[position])
      mIntent.putExtra("image", mFoodImages[position])
      mContext.startActivity(mIntent)
    })
  }

  override fun getItemCount(): Int {
    return mFoodTitle.size
  }

  class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mTitle: TextView = itemView.tvFoodName
    val mImage: ImageView = itemView.ivFood
    val mLayout: RelativeLayout = itemView.layout
  }
}