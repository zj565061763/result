package com.sd.lib.result.ext

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

internal class LibContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        application = context!!.applicationContext as Application
        return true
    }

    override fun query(
        uri: Uri,
        projectionArg: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?,
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?,
    ): Int = 0

    companion object {
        @JvmStatic
        var application: Application? = null
    }
}