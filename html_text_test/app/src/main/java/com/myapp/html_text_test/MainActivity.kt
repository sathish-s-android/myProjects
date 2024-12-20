package com.myapp.html_text_test

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.AlignmentSpan
import android.text.style.URLSpan
import android.webkit.WebView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_test_1).also {
            val spannable:Spannable = (Html.fromHtml(getStr1(),Html.FROM_HTML_MODE_COMPACT) as Spannable)
            val spannable2:Spannable = Html.fromHtml(getStr1()) as Spannable

            spannable2.trim('\n')
            spannable2.trimEnd('\n')

            it.text = spannable
        }

        findViewById<TextView>(R.id.tv_test).also {
            val htmlText = "<p>Paragraph 1</p><p>Paragraph 2</p>"

            val span = Html.fromHtml(htmlText,Html.FROM_HTML_MODE_COMPACT)
            removeTailingCharFromSpanned(span)
            it.text = TextUtils.concat(span,"\u200B")
//            val spannable:Spannable = Html.fromHtml(getStr(),Html.FROM_HTML_MODE_COMPACT) as Spannable
//            val spannable2:Spannable = Html.fromHtml(getStr()) as Spannable
//
//            it.text = spannable
        }

        findViewById<WebView>(R.id.web).also {
            it.loadData(getStr1(), "text/html", "utf-8")
        }
    }

    private fun removeTailingCharFromSpanned(spanned: Spanned) {
        if (spanned is SpannableStringBuilder) {
            var startIndex = spanned.length
            val length = spanned.length
            for (i in 1..6) {
                val currentChar = (spanned[length - i])
                if (length > i && (currentChar == '\n')) {
                    startIndex = length - i
                } else {
                    break
                }
            }
            spanned.delete(startIndex, length)
        }
    }

    fun ssss(sourceView:TextView){
        val text = "First paragraph.\nSecond paragraph."

        // Create a SpannableString
        val spannable = SpannableString(text)

        // Apply a ParagraphStyle (AlignmentSpan) that does not align with the paragraph boundary
        // This span ends midway in the first paragraph, causing the error
        spannable.setSpan(
            AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
            0,
            17,
            Spanned.SPAN_PARAGRAPH
        )

        // Concatenate to trigger the error
        val result = TextUtils.concat(spannable, "Extra text")
    }

}

@SuppressLint("ParcelCreator") //No I18N
class URLSpanNoUnderline(url: String?) : URLSpan(url) {
    override fun updateDrawState(textPaint: TextPaint) {
        super.updateDrawState(textPaint)
        textPaint.isUnderlineText = false
    }
}

fun getStr1():String{
   return "<div>test3" +
           "    <br />" +
           "</div>" +
           "<div>" +
           "    <br />" +
           "</div>" +
           "<div>" +
           "    <br />" +
           "</div>" +
           "<div>test2" +
           "    <br />" +
           "</div>" +
           "<div>" +
           "    <br />" +
           "</div>" +
           "<div>test1" +
           "    <br />" +
           "</div>" +
           "<div>test</div>"
}

fun getStr():String{
   return "<div>test</div>"
}

fun getStr2():String{
   return "<div>test3<br /></div><div><br /></div><div><br /></div><div>test2<br /></div><div><br /></div><div>test1<br /></div><div>test</div>"
}

fun getStr3():String{

   return "<p style=\"margin:0px; font-style:normal; font-variant:normal; font-weight:normal; font-stretch:normal; line-height:1.2\"><span style=\"font-family:Helvetica\"><span style=\"font-size:12px; margin:0px; font-style:normal; font-variant:normal; font-weight:normal; font-stretch:normal; line-height:normal\"><b><span style=\"font-family:Puvi, helvetica, sans-serif, sans-serif\"><span style=\"font-size:1.0714rem\"><span style=\"color:rgb(255, 0, 0)\"><u>Work items:Done:</u></span></span></span></b></span></span><br /><br><ul dir=\"ltr\"><li>added akTwoPaneLayout in&nbsp; existing akNavigation<br /></li></ul><div style=\"line-height:1.2\"><b><span style=\"font-size:1.0714rem\"><span style=\"color:rgb(0, 153, 0)\"><u>Work items:To be done:</u></span></span></b><br /></div><ul dir=\"ltr\"><li style=\"line-height:1.2\">Make akTwoPaneLayout compatible in 3 pane scenario<br /></li><li style=\"line-height:1.2\">Submit akTwoPaneLayout to CRM<br /></li><li style=\"line-height:1.2\">Use akNavigation with akTwoPaneLayout in projects<br /></li></ul><div><br /></div>"
}