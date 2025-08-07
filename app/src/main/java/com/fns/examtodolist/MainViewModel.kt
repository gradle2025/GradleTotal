package com.fns.examtodolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(val repo:RationRespository) :ViewModel(){
     fun insertRationdata(  rationCardTB: RationCardTB){
         viewModelScope.launch {
              repo.insertRationdata(rationCardTB)
         }


    }

    fun getRationDataCategoryWise(categoryId:String): LiveData<List<RationCardTB>> {
        return repo.getRationDataCategoryWise(categoryId)
    }

    fun getAllRationData(): LiveData<List<RationCardTB>>{
        return repo.getAllRationData()
    }

}