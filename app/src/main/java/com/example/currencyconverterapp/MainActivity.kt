package com.example.currencyconverterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.*
import retrofit2.Response
import java.util.*
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private var Currencyinfo: currencyList? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var currencyed = findViewById<View>(R.id.curt) as EditText
        val convertB = findViewById<View>(R.id.b1) as Button
        val option = findViewById<View>(R.id.spinerId) as Spinner
        val total = findViewById<View>(R.id.textpiner) as TextView
        val options = arrayListOf("inr", "usd", "aud", "sar", "cny", "jpy")


        var choose: Int = 0

        if (option != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, options
            )
            option.adapter = adapter

            option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    id: Long
                ) {
                    choose = position
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    total.text = ""


                }

            }
        }


        convertB.setOnClickListener {


            var sel = currencyed.text.toString()
            var currency: Double = sel.toDouble()



            getCurrency(Result = {
                Currencyinfo = it

                when (choose) {
                    0 -> show(convertToEuro(Currencyinfo?.EUR?.INR?.toDouble(), currency))
                    1 -> show(convertToEuro(Currencyinfo?.EUR?.USD?.toDouble(), currency))
                    2 -> show(convertToEuro(Currencyinfo?.EUR?.AUD?.toDouble(), currency))
                    3 -> show(convertToEuro(Currencyinfo?.EUR?.SAR?.toDouble(), currency))
                    4 -> show(convertToEuro(Currencyinfo?.EUR?.CNY?.toDouble(), currency))
                    5 -> show(convertToEuro(Currencyinfo?.EUR?.JPY?.toDouble(), currency))
                }
            })

        }

    }

    fun show(calc: Double) {
     val total = findViewById<View>(R.id.textpiner) as TextView
        total.text = " Total " + convertToEuro
    }


  //Call?.enqueue(object : Callback<currencyList?>) {


            override fun onResponse(

                call: Call<currencyList?>?,
                response: Response<currencyList?>
            ) {

                Log.d("TAG", response.code().toString() + "")
                var displayResponse = ""
                val resource: currencyList? = response.body()

            }


        }


      // option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)


    private fun convertToEuro(i:Double?, sel: Double): Double {
        var s = 0.0
        if (i != null) {
            s = (i * sel)
        }
        return s

    }


    private fun getCurrency(Result: (currencyList?)-> Unit){

        val apiInterface = APIClint().getClint()?.create(APIInterface::class.java)

        if (apiInterface != null) {

            apiInterface.getCurrency()?.enqueue(object : Callback<currencyList> {
                override fun onResponse(
                    call: Call<currencyList>,
                    response: Response<currencyList>
                ) {
                    Result(response.body())

                }


                override fun onFailure(call: Call<currencyList>, t: Throwable) {
                    kotlin.Result(null)
                    Toast.makeText(this, "" + t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }


}