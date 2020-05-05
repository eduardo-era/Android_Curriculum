package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.adapters.SqlitePageAdapter
import com.example.myapplication.handlers.DataBaseHandler
import com.example.myapplication.utils.BaseActivity
import com.example.myapplication.utils.GeneralUtilities
import com.example.myapplication.utils.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_sqlite.*
import kotlinx.android.synthetic.main.sqlite_tablayout.*

class SqliteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        sqliteInit()
    }

    private fun sqliteInit() {
        startViewpager()
        clickInformationButton()
        dbHandler = DataBaseHandler(this, null, null, 1)
    }

    private fun startViewpager() {
        val fragmentAdapter = SqlitePageAdapter(supportFragmentManager)
        sqlite_viewpager.adapter = fragmentAdapter
        sqlite_tabs.setupWithViewPager(sqlite_viewpager)
        sqlite_viewpager.setPageTransformer(true, ZoomOutPageTransformer())
    }

    private fun clickInformationButton() {
        sqlite_help.setOnClickListener {
            GeneralUtilities.informationDialog(this, resources.getString(R.string.information_sqlite))
        }
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, SqliteActivity::class.java))
        lateinit var dbHandler: DataBaseHandler
    }
}