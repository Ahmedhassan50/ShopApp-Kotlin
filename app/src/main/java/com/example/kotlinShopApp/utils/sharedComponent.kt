package com.example.kotlinShopApp.utils

import android.app.AlertDialog
import android.content.Context
import com.example.kotlinShopApp.R

fun showErrorDialog(context:Context,message:String){
    val builder=AlertDialog.Builder(context)
    builder.setTitle(R.string.error)
    builder.setMessage(message)
    builder.setNegativeButton(R.string.ok){dialog,_->dialog.cancel()}

    builder.show()
}