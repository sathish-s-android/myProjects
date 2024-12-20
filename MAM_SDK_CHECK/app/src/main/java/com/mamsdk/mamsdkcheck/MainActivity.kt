package com.mamsdk.mamsdkcheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.mamsdk.mamsdkcheck.databinding.ActivityMainBinding
import com.microsoft.intune.mam.client.identity.MAMPolicyManager
import com.microsoft.intune.mam.policy.MAMUserInfo

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMainBinding
    private val FILE_PICKER_REQUEST_CODE = 2001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        mBinding.getFile.setOnClickListener {
            openChooser()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                // Process the selected file URI (e.g., show file details or display content)
                if (isImageUri(uri)){
                    displayFile(uri)
                }else{
                    displayFileName(uri)
                }

            }
        }
    }

    private fun openChooser(){
        val chooseFile = Intent(Intent.ACTION_GET_CONTENT)
        chooseFile.addCategory(Intent.CATEGORY_OPENABLE)
        chooseFile.setType("*/*")
        ActivityCompat.startActivityForResult(
            this,
            Intent.createChooser(chooseFile, "Choose a file"),
            FILE_PICKER_REQUEST_CODE, null
        )
    }

    private fun displayFile(uri:Uri){
        mBinding.imgView.visibility = View.VISIBLE
        mBinding.tvName.visibility = View.GONE
        mBinding.imgView.setImageURI(uri)
    }
    private fun displayFileName(uri:Uri){
        mBinding.imgView.visibility = View.GONE
        mBinding.tvName.visibility = View.VISIBLE
        mBinding.tvName.text = getFileName(uri)
    }

    private fun getFileName(uri: Uri): String? {
        var fileName: String? = null
        if (uri.scheme == "content") {
            contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (nameIndex != -1 && cursor.moveToFirst()) {
                    fileName = cursor.getString(nameIndex)
                }
            }
        } else if (uri.scheme == "file") {
            // If it's a file URI, get the last path segment
            fileName = uri.lastPathSegment
        }
        return fileName
    }

    private fun isImageUri(uri: Uri): Boolean {
        val mimeType = contentResolver.getType(uri)
        return mimeType?.startsWith("image/") == true
    }
}