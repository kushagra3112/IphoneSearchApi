package com.example.iphonesearchapi.utility
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat

fun showLoadingIndicator(showLoading: Boolean, progressBar: ProgressBar){

   progressBar.visibility = if (showLoading) View.VISIBLE else View.INVISIBLE
}
fun new(a:String,b:String):Boolean{
   return a==b
}