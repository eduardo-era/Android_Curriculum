package com.example.myapplication.presenters

import android.os.CountDownTimer
import com.example.myapplication.interfaces.Async
import com.example.myapplication.views.AsyncView
import kotlinx.android.synthetic.main.activity_async.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class AsyncPresenter(val view: AsyncView):Async.Presenter {

    override fun workingBackground(){

        doAsync {
            Thread.sleep(5000)

            uiThread {
                view.finishMessage()
            }
        }
    }
}