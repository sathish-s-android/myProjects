package com.mamsdk.restrictionmanger

import android.content.Context
import android.content.RestrictionsManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        restriction()
    }
    private fun restriction(){
        var myRestrictionsMgr = this.getSystemService(Context.RESTRICTIONS_SERVICE) as RestrictionsManager

        var appRestrictions: Bundle = myRestrictionsMgr.applicationRestrictions

        val appCanUseCellular: String? =
            if (appRestrictions.containsKey("mdm_restrict_login")) {
                appRestrictions.getString("mdm_restrict_login","mdm_restrict_login default")
            } else {
                // cellularDefault is a boolean using the restriction's default value
                "cellularDefault"
            }

        findViewById<TextView>(R.id.tv_res).text = "$appCanUseCellular"
    }

}

