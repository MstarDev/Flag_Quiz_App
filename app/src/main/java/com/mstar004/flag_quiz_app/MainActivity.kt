package com.mstar004.flag_quiz_app

import Models.Flag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var flagArrayList:ArrayList<Flag>
    var count = 0
    var countryName = ""
    lateinit var buttonArrayList:ArrayList<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonArrayList = ArrayList()
        createObject()
        btnCount()
    }

    private fun createObject() {
        flagArrayList = ArrayList()
        flagArrayList.add(Flag("uzbekistan", R.drawable.uz))
        flagArrayList.add(Flag("argentina", R.drawable.ar))
        flagArrayList.add(Flag("brazil", R.drawable.br))
        flagArrayList.add(Flag("bhutan", R.drawable.bt))
        flagArrayList.add(Flag("canada", R.drawable.ca))
        flagArrayList.add(Flag("china", R.drawable.cn))
        flagArrayList.add(Flag("cuba", R.drawable.cu))
        flagArrayList.add(Flag("germany", R.drawable.de))
        flagArrayList.add(Flag("spain", R.drawable.es))
        flagArrayList.add(Flag("france", R.drawable.fr))
        flagArrayList.add(Flag("england", R.drawable.gb))
        flagArrayList.add(Flag("italy", R.drawable.it))
        flagArrayList.add(Flag("japan", R.drawable.jp))
        flagArrayList.add(Flag("kyrgyzstan", R.drawable.kg))
        flagArrayList.add(Flag("korea", R.drawable.kr))
        flagArrayList.add(Flag("kazakhstan", R.drawable.kz))
        flagArrayList.add(Flag("pakistan", R.drawable.pk))
        flagArrayList.add(Flag("portugal", R.drawable.pt))
        flagArrayList.add(Flag("russia", R.drawable.ru))
        flagArrayList.add(Flag("turkey", R.drawable.tr))
    }
    fun btnCount(){
        image.setImageResource(flagArrayList[count].image!!)
        linear_1_matn.removeAllViews()
        linear_2_btn_1.removeAllViews()
        linear_3_btn_2.removeAllViews()

        countryName = ""

        btnJoyla(flagArrayList[count].name)
    }

    private fun btnJoyla(countryName: String?) {
        val btnArray: ArrayList<Button> = randomBtn(countryName)
        for (i in 0..6){
            linear_2_btn_1.addView(btnArray[i])
        }
        for (i in 7..13){
            linear_3_btn_2.addView(btnArray[i])
        }
    }
    private fun randomBtn(countryName: String?): java.util.ArrayList<Button> {
        val array = ArrayList<Button>()
        val arrayText = ArrayList<String>()

        for (c in countryName!!){
            arrayText.add(c.toString())

        }
        if (arrayText.size != 14){
            val str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            for (i in arrayText.size until 14){
                val random = Random().nextInt(str.length)
                arrayText.add(str[random].toString())
            }
        }
        arrayText.shuffle()

        for (i in 0 until arrayText.size) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1.0f)
            button.text = arrayText[i]
            button.setOnClickListener(this)
            array.add(button)
        }
        return array
    }

    override fun onClick(v: View?) {
        val button1 = v as Button
        if(buttonArrayList.contains(button1)){
            linear_1_matn.removeView(button1)
            var hasC = false
            linear_2_btn_1.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {

                    button.visibility = View.VISIBLE
                    countryName = countryName.substring(0, countryName.length - 1)
                    hasC = true
                }
            }
                linear_3_btn_2.children.forEach {button ->
                    if ((button as Button).text.toString() == button1.text.toString()){
                        button.visibility = View.VISIBLE
                        if (!hasC){
                            countryName = countryName.substring(0, countryName.length-1)
                        }

                    }
                }

        }else{
            button1.visibility  = View.INVISIBLE
            countryName += button1.text.toString().toUpperCase()
            val button2 = Button(this)
            button2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
            button2.text = button1.text
            button2.setOnClickListener(this)
            buttonArrayList.add(button2)
            linear_1_matn.addView(button2)

            togriMatn()
        }

    }

    private fun togriMatn() {
        if (countryName == flagArrayList[count].name?.toUpperCase()){
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
            if (count == flagArrayList.size-1){
                count = 0
            }else{
                count++
            }
            btnCount()
        }else{

            if (countryName.length == flagArrayList[count].name?.length){
                Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
                linear_1_matn.removeAllViews()
                linear_2_btn_1.removeAllViews()
                linear_3_btn_2.removeAllViews()
                btnJoyla(flagArrayList[count].name)
                countryName = ""
            }
        }

    }
}