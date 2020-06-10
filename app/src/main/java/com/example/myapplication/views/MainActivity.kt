package com.example.myapplication.views

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R
import com.example.myapplication.utils.BaseActivity
import com.example.myapplication.utils.GeneralUtilities
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toast_no_internet.*
import kotlin.math.hypot
import kotlin.math.max


class MainActivity : BaseActivity() {

    var isOpen: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainInit()
    }

    private fun mainInit(){
        clickButtonSqlite()
        clickButtonFirebase()
        clickFloattingButton()
        clickMainButtonCV()
        clickButtonPhone()
        clickRetrofit()
        clickAsyncButton()
        clickButtonMaps()
    }

    private fun clickButtonSqlite(){
        main_button_sqlite.setOnClickListener {
            SqliteActivity.start(this)
        }
    }

    private fun clickButtonFirebase(){
        main_button_firebase.setOnClickListener {
            if (GeneralUtilities.isNetworkAvaliable(this)){
                FirebaseActivity.start(this)
            }else{
                toastNoInternet()
            }
        }
    }

    private fun clickRetrofit(){
        main_button_retrofit.setOnClickListener {
            if(GeneralUtilities.isNetworkAvaliable(this)){
                RetrofitView.start(this)
            }else{
                toastNoInternet()
            }
        }
    }

    private fun clickAsyncButton(){
        main_button_async.setOnClickListener {
            AsyncView.start(this)
        }
    }

    private fun clickFloattingButton(){
        main_floatting_buttons.setOnClickListener {
            circularReveal()
        }
    }

    private fun clickMainButtonCV(){
        main_button_curriculum.setOnClickListener {
            CvPdfView.start(this)
        }
    }

    private fun clickButtonPhone(){
        main_button_phone.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + "5521001770")
                startActivity(intent)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun clickButtonMaps(){
        main_button_maps.setOnClickListener {
            MapsActivity.start(this)
        }
    }

    private fun circularReveal(){

        if(layoutButtons.visibility == View.GONE){
            val x: Int = main_screen.right
            val y: Int = main_screen.bottom

            val startRadius = 0
            val endRadius = hypot(all_main_screen.width.toDouble(), all_main_screen.height.toDouble()).toInt()

            main_floatting_buttons.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(resources,android.R.color.white,null))
            main_floatting_buttons.setImageResource(R.drawable.cross_gray)

            val anim = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius.toFloat(), endRadius.toFloat())
            layoutButtons.visibility = View.VISIBLE
            anim.start()
            isOpen = true

        }else{

            val x = layoutButtons.right
            val y = layoutButtons.bottom

            val startRadius: Int = max(main_screen.width, main_screen.height)
            val endRadius = 0

            main_floatting_buttons.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.colorAccent, null))
            main_floatting_buttons.setImageResource(R.drawable.plus_white)

            val anim = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius.toFloat(), endRadius.toFloat())

            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    layoutButtons.visibility = View.GONE
                }
                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
            anim.start()
            isOpen = false
        }
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
        fun start(context: Context) = context.startActivity(Intent(context, MainActivity::class.java))
    }
}
