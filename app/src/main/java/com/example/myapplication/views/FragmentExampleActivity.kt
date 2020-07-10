package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R
import com.example.myapplication.fragments.FragmenExampleOne
import com.example.myapplication.fragments.FragmentExampleTwo
import com.example.myapplication.utils.GeneralUtilities
import kotlinx.android.synthetic.main.activity_fragment_example.*
import kotlinx.android.synthetic.main.toast_no_internet.*

class FragmentExampleActivity: FragmentActivity() {

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_example)

        init()
    }

    private fun init(){
        clickShowFragmentOne()
        clickShowFragmentTwo()
    }

    private fun clickShowFragmentOne(){
        button_show_fragment_one.setOnClickListener {
            if (GeneralUtilities.isNetworkAvaliable(this)){
                showFragmentOne()
            }else{
                toastNoInternet()
            }
        }
    }

    private fun clickShowFragmentTwo(){
        button_show_fragment_two.setOnClickListener {
            if (GeneralUtilities.isNetworkAvaliable(this)){
                showFragmentTwo()
            }else{
                toastNoInternet()
            }
        }
    }

    private fun showFragmentOne(){
        val transaction = manager.beginTransaction()
        val fragment = FragmenExampleOne()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showFragmentTwo(){
        val transaction = manager.beginTransaction()
        val fragment = FragmentExampleTwo()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun toastNoInternet(){
        val customToastLayout = layoutInflater.inflate(R.layout.toast_no_internet,custom_toast_container)
        val customToast = Toast(this)
        customToast.view = customToastLayout
        customToast.setGravity(Gravity.CENTER,0,0)
        customToast.duration = Toast.LENGTH_SHORT
        customToast.show()
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, FragmentExampleActivity::class.java))
    }

}