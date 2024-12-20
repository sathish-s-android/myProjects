package com.myapp.db_encryption

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.myapp.db_encryption_test.R
import com.myapp.db_encryption_test.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val localRepo:LocalRepository by lazy{
        LocalRepository(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        mBinding.save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                localRepo.saveNotes(
                    Notes(
                        mBinding.titleEd.text.toString(),
                        mBinding.contentEd.text.toString(),
                    )
                )
            }
        }

        mBinding.printTable.setOnClickListener {

//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse("zprojects://some_data")
//                startActivity(intent)

            CoroutineScope(Dispatchers.IO).launch {
                localRepo.getNotes().also { notes->
                    notes.map {note->
                        Log.d("Sathish_SSSS", "Title : ${note.title}")
                        Log.d("Sathish_SSSS", "Content : ${note.content}")
                        Log.d("Sathish_SSSS", "")
                    }
                }

                Log.d("Sathish_SSSS", "**********************************************")
                Log.d("Sathish_SSSS", "")
            }
        }
    }
}