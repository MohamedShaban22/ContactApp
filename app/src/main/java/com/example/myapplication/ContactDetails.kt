package com.example.myapplication



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityContactDetailsBinding


class ContactDetails : AppCompatActivity() {
    lateinit var viewBinding:ActivityContactDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val userName=intent.getStringExtra("name")
        val phoneNum=intent.getStringExtra("phone")
        val descriptions=intent.getStringExtra("description")

        viewBinding.nameDetailsTv.text=userName.toString()
        viewBinding.phoneDetailsTv.text=phoneNum.toString()
        viewBinding.descriptionDetailsTv.text=descriptions.toString()

        backClick()

    }
    fun backClick(){
        viewBinding.backImage.setOnClickListener{
            finish()
        }

    }
}