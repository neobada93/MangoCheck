package com.todo.check

import android.content.Context

class Utils {
    companion object {
        fun projectName(context: Context) = run {
            context.resources.getText(
                context.resources.getIdentifier(
                    "app_name",
                    "string", context.packageName
                )
            )
        }
    }
}