package com.fns.examtodolist

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fns.examtodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val dao:RationDao=RationDatabase.getConnection(applicationContext).getRationdata()
      val repo=RationRespository(dao)
        val viewModel= ViewModelProvider(this,MainViewModelFactory(repo)).get(MainViewModel::class.java)


       binding.btnInsert.setOnClickListener{
           val newEntry = RationCardTB(
               id=0,
               applicantName = binding.etName.text.toString(),
               aadhaarNumber = binding.etAadhaar.text.toString(),
               Category = binding.spCategory.selectedItem.toString()
           )
           var isError=isValid()
           if(isError) {
               viewModel.insertRationdata(
                   newEntry
               )
               binding.etName.setText("")
               binding.etAadhaar.setText("")
               binding.spCategory.setSelection(0)
               Toast.makeText(binding.root.context, "You have successfully added", Toast.LENGTH_SHORT).show()

           }


       }
        binding.fabView.setOnClickListener{
            intent= Intent(this,ViewRationData::class.java)
            startActivity(intent)
            finish()
        }


    }
    fun isValid(): Boolean {
        if (binding.etName.text.isBlank()) {
            Toast.makeText(binding.root.context, "Please enter applicant name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.etAadhaar.text.isBlank() || binding.etAadhaar.text.length != 12) {
            Toast.makeText(binding.root.context, "Aadhaar number must be 12 digits", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.spCategory.selectedItem.toString().trim() == "Please Select") {
            Toast.makeText(binding.root.context, "Please select a valid category", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }



}