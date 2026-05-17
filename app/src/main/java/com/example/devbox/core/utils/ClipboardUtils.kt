package com.example.devbox.core.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object ClipboardUtils {

    fun copyToClipboard(
        context: Context,
        text: String
    ) {

        val clipboard =
            context.getSystemService(Context.CLIPBOARD_SERVICE)
                    as ClipboardManager

        val clip = ClipData.newPlainText(
            "text",
            text
        )

        clipboard.setPrimaryClip(clip)
    }

    fun getClipboardText(
        context: Context
    ): String {

        val clipboard =
            context.getSystemService(Context.CLIPBOARD_SERVICE)
                    as ClipboardManager

        return clipboard.primaryClip
            ?.getItemAt(0)
            ?.text
            ?.toString()
            ?: ""
    }
}