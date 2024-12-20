package com.myapp.myapplication

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class DownloadCompleteReceiver: BroadcastReceiver() {

    private var manager:DownloadManager?=null

    fun setManager(manager:DownloadManager){
        this.manager = manager
    }
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("Sathish_SSS", "onReceive: Intent = $intent")
        if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            val query = DownloadManager.Query()
            query.setFilterById(downloadId)
            val cursor = manager?.query(query)
            cursor?.also {
                if (cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                    val status = cursor.getInt(columnIndex)

                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        Toast.makeText(context, "Download completed successfully", Toast.LENGTH_SHORT).show()
                        // Use the downloaded file path
                        val downloadPath = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                    } else {
                        Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
                    }
                }
//                context.unregisterReceiver(this)
                cursor.close()
            }
        }
    }


    interface Manager {
        fun getDownloadManager():DownloadManager
    }
}