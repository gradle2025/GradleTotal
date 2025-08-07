package com.fns.examtodolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fns.examtodolist.databinding.ActivityMainBinding
import com.fns.examtodolist.databinding.ContentViewrationdataBinding

class ViewRationData : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ContentViewrationdataBinding
    private lateinit var adapter: RationAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentViewrationdataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dao: RationDao = RationDatabase.getConnection(applicationContext).getRationdata()
        val repo = RationRespository(dao)
        val viewModel =
            ViewModelProvider(this, MainViewModelFactory(repo)).get(MainViewModel::class.java)

        adapter = RationAdapter()
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
        binding.spCategory.setSelection(1)
        viewModel.getAllRationData().observe(this) { rations ->
            adapter.setData(rations) // this updates your RecyclerView with fresh data
        }
        binding.fabView.setOnClickListener{
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCategory = parent.getItemAtPosition(position).toString()

                if (selectedCategory == "ALL") {
                    viewModel.getAllRationData().observe(this@ViewRationData) { rations ->
                        adapter.setData(rations) // this updates your RecyclerView with fresh data
                    }
                }
                  else if (selectedCategory != "Please Select"||selectedCategory!="ALL") {
                        viewModel.getRationDataCategoryWise(selectedCategory).observe(this@ViewRationData) { list ->
                            adapter.setData(list)
                        }
                } else {
                    adapter.setData(emptyList()) // clear list
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }





    }


}