package com.example.iphonesearchapi.utility
import android.view.View
import android.widget.ProgressBar

fun showLoadingIndicator(showLoading: Boolean, progressBar: ProgressBar){

   progressBar.visibility = if (showLoading) View.VISIBLE else View.INVISIBLE
}
fun new(a:String,b:String):Boolean{
   return a==b
}
