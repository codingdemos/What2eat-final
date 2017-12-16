/*
 * Copyright (c) 2017 Razeware LLC
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

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

  var mFoodName = arrayOf("Banana", "Cheese", "Chocolate cookie", "Chocolate chip cookie",
      "Cupcake", "Pancakes")
  var mFoodImage = intArrayOf(R.drawable.food_banana, R.drawable.food_cheese,
      R.drawable.food_cookie_chocolate, R.drawable.food_cookie_chocolatechip,
      R.drawable.food_cupcake, R.drawable.food_pancakes)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    toolbar.inflateMenu(R.menu.menu_toolbar)
    toolbar.title = resources.getString(R.string.app_name)
    val mLayoutManger = LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL, false)
    recyclerView.layoutManager = mLayoutManger
    recyclerView.adapter = FoodAdapter(this, mFoodName, mFoodImage)
    TapTargetView.showFor(this, TapTarget.forToolbarOverflow(toolbar,
        getString(R.string.label_app_settings), getString(R.string.description_app_setting))
        .cancelable(false).tintTarget(true), object : TapTargetView.Listener() {
      override fun onTargetClick(view: TapTargetView) {
        super.onTargetClick(view)
        view.dismiss(true)
      }
    })

    TapTargetView.showFor(this, TapTarget.forToolbarMenuItem(toolbar, R.id.action_search,
        getString(R.string.label_search), getString(R.string.description_search))
        .cancelable(false).tintTarget(true), object : TapTargetView.Listener() {
      override fun onTargetClick(view: TapTargetView) {
        super.onTargetClick(view)
        view.dismiss(true)
      }
    })

  }

  override fun onBackPressed() {
    val mAlertDialog = AlertDialog.Builder(this).create()
    mAlertDialog.setMessage("Are you sure you want to exit ${resources.getString(R.string.app_name)}")
    mAlertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.label_ok),
        { dialogInterface, i ->
          val mIntent = Intent(Intent.ACTION_MAIN)
          mIntent.addCategory(Intent.CATEGORY_HOME)
          mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
          startActivity(mIntent)
        })
    mAlertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.label_no),
        { dialogInterface, i ->
          dialogInterface.dismiss()
        })
    mAlertDialog.show()
    TapTargetView.showFor(mAlertDialog,
        TapTarget.forView(mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE),
            getString(R.string.label_exit_app),
            getString(R.string.description_exit))
            .cancelable(false).tintTarget(false), object : TapTargetView.Listener() {
      override fun onTargetClick(view: TapTargetView) {
        super.onTargetClick(view)
        view.dismiss(true)
      }
    })
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_toolbar, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.action_settings) {
      startActivity(Intent(this, SettingActivity::class.java))
    }
    return true
  }
}
