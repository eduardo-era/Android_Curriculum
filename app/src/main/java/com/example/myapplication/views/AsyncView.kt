package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.interfaces.Async
import com.example.myapplication.presenters.AsyncPresenter
import com.example.myapplication.utils.BaseActivity

class AsyncView:BaseActivity(),Async.View {

    val presenter = AsyncPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activuty_async)

        init()
    }

    private fun init(){
        createButton()
    }

    private fun createButton(){
        val asyncButton = Button(this)
        val color = resources.getColor(R.color.colorPrimary)
        val layout = findViewById<LinearLayout>(R.id.button_container_async)
        asyncButton.background = resources.getDrawable(R.drawable.rounded_corners_edge_blue_background_white)
        asyncButton.setText(R.string.make_async)
        asyncButton.setTextColor(color)
        asyncButton.textSize = 20f
        asyncButton.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layout.setPadding(200,0,200,0)
        layout.addView(asyncButton)

        asyncButton.setOnClickListener {
            presenter.workingBackground()
        }
    }

    override fun finishMessage(){
        Toast.makeText(this,"Tarea en segundo plano finalizada",Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, AsyncView::class.java))
    }
}