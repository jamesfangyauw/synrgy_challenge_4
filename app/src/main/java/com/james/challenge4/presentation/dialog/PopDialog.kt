package com.james.challenge4.presentation.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.james.challenge4.R

class PopDialog {
    fun showDialog(
        context: Context,
        positive: DialogInterface.OnClickListener,
        negative: DialogInterface.OnClickListener,
        title: String,
        subTitle: String,
    ){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(subTitle)
        builder.setPositiveButton(context.getString(R.string.yes), positive)
        builder.setNegativeButton(context.getString(R.string.no), negative)
        builder.show()
    }
}