package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.utils.BaseActivity
import com.github.barteksc.pdfviewer.PDFView

class CvPdfView:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_cv)

        val pdfview = findViewById<PDFView>(R.id.pdfViewer);
        pdfview.useBestQuality(true);
        pdfview.enableSwipe(true);
        pdfview.fitToWidth();
        pdfview.fromAsset("Curriculum _Vitae.pdf").load();
    }

    companion object {
        fun start(context: Context) = context.startActivity(Intent(context, CvPdfView::class.java))
    }
}