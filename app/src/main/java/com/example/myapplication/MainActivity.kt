package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.BottomAddFragment.BottomAddFragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.ContactModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var vieBinding: ActivityMainBinding

   lateinit var contactAdapter:ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vieBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vieBinding.root)
        addButtonClick()
        loadContacts()
        itemClick()
        deleteItem()
        callContact()
    }

    private fun itemClick() {
        contactAdapter.onItemClickListener=object :ContactAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, contact: ContactModel) {
               val intent=Intent(this@MainActivity,ContactDetails::class.java)
                intent.putExtra("name",contact.name)
                intent.putExtra("phone",contact.phone)
                intent.putExtra("description",contact.description)
                startActivity(intent)
            }
        }
    }
    private fun deleteItem(){
        contactAdapter.onItemDeleteListener=object :ContactAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, contact: ContactModel) {
                MyDataBase.getInstance(this@MainActivity)
                    .ContactDao().deleteContact(contact)
                val copyContactList=MyDataBase.getInstance(this@MainActivity)
                    .ContactDao().getAllContacts()
                contactAdapter.refreshList(copyContactList)

            }
        }
    }

    private fun callContact(){
        contactAdapter.onItemCallListener=object :ContactAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, contact: ContactModel) {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + contact.phone)
                startActivity(dialIntent)
            }
        }
    }
    private fun loadContacts() {
        val contactsList=MyDataBase.getInstance(this)
            .ContactDao().getAllContacts()
        contactAdapter=ContactAdapter(contactsList)
        vieBinding.contactRecyclerView.adapter=contactAdapter

    }
    /*private fun showToastAddedMessage() {
        val bottomAddFragment = BottomAddFragment()
        bottomAddFragment?.onAddContactListener = BottomAddFragment.OnAddContactListener {
            //Toast.makeText(this@MainActivity,"Task Added Successfully",Toast.LENGTH_LONG )
            //.show()
            Snackbar.make(vieBinding.root, "Task Add Successful", Snackbar.LENGTH_LONG)
                .show()
        }
    }*/

    fun addButtonClick() {
        vieBinding.addBtn.setOnClickListener(View.OnClickListener {
            val addBottomFragment = BottomAddFragment()

            addBottomFragment.show(supportFragmentManager, "")
            addBottomFragment?.onAddContactListener = BottomAddFragment.OnAddContactListener {
                Snackbar.make(vieBinding.root, "Task Add Successful", Snackbar.LENGTH_LONG)
                    .show()
                val copyContactList=MyDataBase.getInstance(this)
                    .ContactDao().getAllContacts()
                contactAdapter.refreshList(copyContactList)
            }
        })
    }
}
