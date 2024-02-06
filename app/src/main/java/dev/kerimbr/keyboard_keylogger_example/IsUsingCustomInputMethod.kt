package dev.kerimbr.keyboard_keylogger_example

import android.content.Context
import android.content.pm.ApplicationInfo
import android.provider.Settings
import android.view.inputmethod.InputMethodManager

fun isUsingCustomInputMethod(context: Context): Boolean {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val mInputMethodProperties = imm.enabledInputMethodList
    for (i in 0 until mInputMethodProperties.size) {
        val imi = mInputMethodProperties[i]
        if (imi.id == Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.DEFAULT_INPUT_METHOD
            )
        ) {
            if (imi.serviceInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                return true
            }
        }
    }
    return false
}