package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.utils.BaseActivity
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.android.synthetic.main.toolbar.*

class CvPdfView:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_cv)

        setTitleToolbarAndHelp()

        val pdfview = findViewById<PDFView>(R.id.pdfViewer);
        pdfview.useBestQuality(true);
        pdfview.enableSwipe(true);
        pdfview.fitToWidth();
        pdfview.fromAsset("Curriculum _Vitae.pdf").load();
    }

    private fun setTitleToolbarAndHelp(){
        toolbar_title.text = resources.getString(R.string.curriculum)
        help_toolbar.visibility = View.GONE
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, CvPdfView::class.java))
    }
}