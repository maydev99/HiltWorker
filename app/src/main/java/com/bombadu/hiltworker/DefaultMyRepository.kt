package com.bombadu.hiltworker

import android.util.Log
import javax.inject.Inject

class DefaultMyRepository @Inject constructor() : MyRepository{

    override fun printToLog() {
        Log.i("TAG", "Print to Log Executed")

    }
}