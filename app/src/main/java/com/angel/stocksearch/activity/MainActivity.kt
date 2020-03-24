package com.angel.stocksearch.activity

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.angel.stocksearch.R
import com.angel.stocksearch.adapter.FriendsAdapter
import com.angel.stocksearch.util.ConversionUtil
import com.angel.stocksearch.viewmodel.MainViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAppBar()
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        subscribe()
        mViewModel.fetchFriends()
    }

    override fun onDestroy() {
        unsubscribe()
        super.onDestroy()
    }

    private fun setupAppBar() {
        val toolbarHt = resources.getDimensionPixelSize(R.dimen.toolbar_ht)
        val titleLeftMargin = resources.getDimensionPixelSize(R.dimen.toolbar_title_margin_start)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val scrnW = displayMetrics.widthPixels
        val titleMaxFontSize = resources.getDimensionPixelSize(R.dimen.title_txt_size_big);
        val titleFontSizeDiff = titleMaxFontSize - resources.getDimensionPixelSize(
            R.dimen.title_txt_size_small
        );

        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            lnr_title.top = toolbarHt
            val totalShiftAmt = (scrnW - txt_title_1_small.width - txt_title_2_small.width) / 2 - titleLeftMargin
            val factor = verticalOffset * 1.0f / toolbarHt
            lnr_title.left = (titleLeftMargin - factor * totalShiftAmt).toInt()
            val fontSize = ConversionUtil.convertPixelsToDp(titleMaxFontSize + factor * titleFontSizeDiff, this)
            txt_title_1.textSize = fontSize
            txt_title_2.textSize = fontSize
        })
    }

    private fun subscribe() {
        mViewModel.friends.observe(this, Observer {
            recycler_view.layoutManager = LinearLayoutManager(this)
            recycler_view.adapter = FriendsAdapter(it.users)
        })
    }

    private fun unsubscribe() {
        mViewModel.friends.removeObservers(this)
    }
}
