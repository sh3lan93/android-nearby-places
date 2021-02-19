package com.shalan.nearby.base.activity

import android.os.Bundle

interface IActivity {

    val layoutId: Int

    fun onCreateInit(savedInstance: Bundle?)

}
