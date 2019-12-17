package com.example.myapplication.view

import android.content.Context
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView

public class ColoredTextView(context : Context) : TextView(context){

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
    }

}