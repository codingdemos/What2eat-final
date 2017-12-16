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

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence
import kotlinx.android.synthetic.main.activity_food_detail.*

class FoodDetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_food_detail)

    toolbar.title = intent.getStringExtra("title")
    toolbar.setNavigationOnClickListener(View.OnClickListener { view ->
      val mIntent = Intent(this, MainActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
      intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
      startActivity(mIntent)
    })
    foodImage.setImageResource(intent.getIntExtra("image", R.drawable.food_banana))

    val mIntent = Intent()
    btnShare.setOnClickListener(View.OnClickListener { view ->
      mIntent.action = Intent.ACTION_SEND
      mIntent.putExtra(Intent.EXTRA_TEXT, "I'm eating ${toolbar.title}")
      mIntent.type = "text/plain"
      startActivity(Intent.createChooser(mIntent, getString(R.string.label_sendto)))
    })

    btnStore.setOnClickListener(View.OnClickListener { view ->
      mIntent.action = Intent.ACTION_VIEW
      mIntent.data = Uri.parse("https://www.freshdirect.com/index.jsp")
      startActivity(mIntent)
    })

    if(Util().getTutorialStatus(this)) {
      TapTargetSequence(this)
          .targets(
              TapTarget.forView(btnShare, getString(R.string.label_share_food),
                  getString(R.string.description_share_food))
                  .cancelable(false).transparentTarget(true).targetRadius(70),
              TapTarget.forView(btnStore, getString(R.string.label_buy_food),
                  getString(R.string.description_buy_food)).cancelable(false).
                  transparentTarget(true).targetRadius(70),
              TapTarget.forToolbarNavigationIcon(toolbar, getString(R.string.label_back_arrow),
                  getString(R.string.description_back_arrow)).cancelable(false)
                  .tintTarget(true)).listener(object : TapTargetSequence.Listener {
        override fun onSequenceStep(lastTarget: TapTarget?, targetClicked: Boolean) {

        }
        override fun onSequenceFinish() {
          Toast.makeText(this@FoodDetailActivity, getString(R.string.msg_tutorial_complete),
              Toast.LENGTH_LONG).show()
          Util().storeTutorialStatus(this@FoodDetailActivity, false)
        }

        override fun onSequenceCanceled(lastTarget: TapTarget) {

        }
      }).start()
    }
  }
}
