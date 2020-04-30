package com.example.myapplication.views

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.adapters.SqlitePageAdapter
import com.example.myapplication.handlers.DataBaseHandler
import com.example.myapplication.utils.BaseActivity
import com.example.myapplication.utils.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_sqlite.*
import kotlinx.android.synthetic.main.sqlite_tablayout.*
import java.time.zone.ZoneOffsetTransition

class SqliteActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
        sqliteInit()
    }

    private fun sqliteInit(){
        startViewpager()
        dbHandler = DataBaseHandler(this,null, null, 1)
    }

    private fun startViewpager(){
        val fragmentAdapter = SqlitePageAdapter(supportFragmentManager)
        sqlite_viewpager.adapter = fragmentAdapter
        sqlite_tabs.setupWithViewPager(sqlite_viewpager)
        sqlite_viewpager.setPageTransformer(true, ZoomOutPageTransformer())
    }

    private fun clickInformationButton(){
        sqlite_help.setOnClickListener {

        }
    }

    private fun showInformation(){
        val dialog = AlertDialog.Builder(this)

    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, SqliteActivity::class.java))
        lateinit var dbHandler: DataBaseHandler
    }
}