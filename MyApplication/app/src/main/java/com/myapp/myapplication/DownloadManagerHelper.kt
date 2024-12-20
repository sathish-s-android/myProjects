package com.myapp.myapplication


import android.app.Application
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.File

class DownloadManagerHelper(private val context: Context) {

    private val downloadManager: DownloadManager by lazy {
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }
    private var downloadCompleteReceiver:BroadcastReceiver?=null
    fun downloadFileToInternalStorage(fileName: String) {



        val request = DownloadManager.Request(Uri.parse("https://contacts.zoho.com/file/download?fs=thumb&ID=758207345"))
            .setTitle("Downloading...")
            .setDescription("Downloading $fileName")
        request.addRequestHeader("Authorization", "Zoho-oauthtoken 1002.cb237823d7ff5f22d9f40aae3d811726.aab4f9d43fb313f28e49f641e9270246")
        request.addRequestHeader("User-Agent","Zoho Qntrl Development/3.0.6_tc1-dev (Linux; U; Android 14; SM-S921B Build/UP1A.231005.007) DC:us;SSO:false;L:en_IN;TZ:Asia/Kolkata IST;D:P;M:samsung;Android" )
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationUri(getFileUriForFile(getAttachmentsCacheDirectory(), fileName,context))
//            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
//            .setScannedAfterDownload(true)

        val downloadId = downloadManager.enqueue(request)

        registor()

        // Register a broadcast receiver to track download progress and completion
//     val downloadCompleteReceiver   = object : BroadcastReceiver() {
//            override fun onReceive(context: Context, intent: Intent) {
//
//                Log.d("Sathish_SSS", "onReceive: $intent")
//
//                if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
//                    val query = DownloadManager.Query()
//                    query.setFilterById(downloadId)
//                    val cursor = downloadManager.query(query)
//
//                    if (cursor.moveToFirst()) {
//                        val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
//                        val status = cursor.getInt(columnIndex)
//
//                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
//                            Toast.makeText(context, "Download completed successfully", Toast.LENGTH_SHORT).show()
//                            // Use the downloaded file path
//                            val downloadPath = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
//                        } else {
//                            Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    cursor.close()
//                    context.unregisterReceiver(this)
//                }
//            }
//        }
//
//        context.registerReceiver(downloadCompleteReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),RECEIVER_EXPORTED)
//

    }

    private fun registor(){
        if (downloadCompleteReceiver == null){
            downloadCompleteReceiver = DownloadCompleteReceiver().apply {
                setManager(manager = downloadManager)
            }

            context.registerReceiver(downloadCompleteReceiver,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),RECEIVER_EXPORTED
            )
        }
    }

    fun getFileUriForFile(directory: File,
                          subPath: String,
                          context: Context) = Uri.withAppendedPath(Uri.fromFile(directory), subPath)

    fun getAttachmentsCacheDirectory(): File
    {
        return createDirectories(getCacheDirectory(context).absolutePath + "/files/attachments")    //No I18N
    }


    fun createDirectories(directoryPath: String): File
    {
        val director = File(directoryPath)
        if (!director.isDirectory)
        {
            director.mkdirs()
        }
        return director
    }

    fun getCacheDirectory(context: Context): File
    {
        val cacheDir: File? = context.externalCacheDir
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED && cacheDir != null && cacheDir.freeSpace > 0)
        {
            cacheDir
        }
        else
        {
            context.cacheDir
        }
    }
}