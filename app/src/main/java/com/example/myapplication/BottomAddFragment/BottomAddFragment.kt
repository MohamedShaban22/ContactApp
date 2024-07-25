package com.example.myapplication.BottomAddFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.MyDataBase
import com.example.myapplication.databinding.BottomAddFrragmentBinding
import com.example.myapplication.model.ContactModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.FieldPosition


class BottomAddFragment : BottomSheetDialogFragment() {
    lateinit var viewBinding:BottomAddFrragmentBinding
    lateinit var disclist :MutableList<ContactModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = BottomAddFrragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveBtnClick()

    }

    fun saveBtnClick(){

        viewBinding.btnSave.setOnClickListener {

            var name=viewBinding.nameEt.text.toString().trim()
            var phone=viewBinding.phoneEt.text.toString().trim()
            var discription=viewBinding.descriptionEt.text.toString().trim()

            if(name.length<3){
                viewBinding.nameEt.error="name length is should be large than 3 letters"
            }
            else if(phone.length!=11){
                viewBinding.phoneEt.error="phone number should be 11 digits"

            }
            else{
                val contact=ContactModel(
                    name=viewBinding.nameEt.text.toString().trim(),
                    phone=viewBinding.phoneEt.text.toString().trim(),
                    description =viewBinding.descriptionEt.text.toString().trim()
                )
                MyDataBase.getInstance(requireContext()).ContactDao().insertContact(contact)
                onAddContactListener?.onContactAdd()
                dismiss()
                           }

        }


    }

    var onAddContactListener:OnAddContactListener?=null
    fun interface OnAddContactListener{
    fun onContactAdd()
}
}