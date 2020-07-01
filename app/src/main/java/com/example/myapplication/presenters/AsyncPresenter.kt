package com.example.myapplication.presenters

import com.example.myapplication.interfaces.Async
import com.example.myapplication.views.AsyncView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AsyncPresenter(val view: AsyncView):Async.Presenter {

    override fun workingBackground(){
        doAsync {
            Thread.sleep(1000)
            uiThread {
                view.finishMessage()
            }
        }
    }
}