package com.fns.examtodolist

import androidx.lifecycle.LiveData
import androidx.room.Query


class RationRespository(val rationdao :RationDao) {


 suspend fun insertRationdata(  rationCardTB: RationCardTB){
      rationdao.insertRationdata(rationCardTB)

 }

    fun getRationDataCategoryWise(categoryId:String): LiveData<List<RationCardTB>>{
        return rationdao.getRationDataCategoryWise(categoryId)
    }


    fun getAllRationData(): LiveData<List<RationCardTB>>{
        return rationdao.getAllRationData()
    }
}