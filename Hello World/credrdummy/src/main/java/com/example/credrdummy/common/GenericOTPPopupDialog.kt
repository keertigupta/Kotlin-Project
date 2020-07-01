package connect.com.credr.connect.common

import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Window
import android.widget.EditText
import connect.com.credr.connect.R
import connect.com.credr.connect.utils.Utils

import kotlinx.android.synthetic.main.dialog_otp.*


abstract class GenericOTPPopupDialog (mcontext: Context) : Dialog(mcontext, R.style.FullWidth_Dialog) {



    //var bean: RunnerLeadList? = d


    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_otp)
        val et1 :EditText= findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)
        val et3 = findViewById<EditText>(R.id.et3)
        val et4 = findViewById<EditText>(R.id.et4)
        val et5 = findViewById<EditText>(R.id.et5)
        val et6 = findViewById<EditText>(R.id.et6)
        et1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    et2.requestFocus()
                } else if (s.length == 0) {
                    et1.clearFocus()
                }
            }
        })
        et2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    et3.requestFocus()
                } else if (s.length == 0) {
                    et1.requestFocus()
                }
            }
        })
        et3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    et4.requestFocus()
                } else if (s.length == 0) {
                    et2.requestFocus()
                }
            }
        })
        et4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    et5.requestFocus()
                } else if (s.length == 0) {
                    et3.requestFocus()
                }
            }
        })
        et5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    et6.requestFocus()
                } else if (s.length == 0) {
                    et4.requestFocus()
                }
            }
        })
        et6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length == 1) {
                    et6.clearFocus()
                } else if (s.length == 0) {
                    et5.requestFocus()
                }
            }
        })
        btnYes.setOnClickListener {


            val d1 =if(et1.text!=null) et1.text.toString() else ""
            val d2:String=if(et2.text!=null) et2.text.toString() else ""
            val d3:String=if(et3.text!=null) et3.text.toString() else ""
            val d4:String=if(et4.text!=null) et4.text.toString() else ""
            val d5:String=if(et5.text!=null) et5.text.toString() else ""
            val d6:String=if(et6.text!=null) et6.text.toString() else ""

            if (TextUtils.isEmpty(d1) || TextUtils.isEmpty(d2) || TextUtils.isEmpty(d3)
                    || TextUtils.isEmpty(d4) || TextUtils.isEmpty(d5) || TextUtils.isEmpty(d6)) {
                Utils.toast("Please enter OTP", context)
            } else {
                val otp = d1+d2+d3+d4+d5+d6
                verifyOtp(otp)
            }


        }
    }

    abstract fun verifyOtp(otp: String)




}
