package com.shalan.nearby.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.shalan.nearby.base.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<ViewModel : BaseViewModel, Binding : ViewDataBinding>(clazz: KClass<ViewModel>) :
    AppCompatActivity(), IActivity {

    protected lateinit var binding: Binding
    protected val viewmodel by viewModel(clazz = clazz)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewmodel)
        viewmodel.startLogic()
        onCreateInit(savedInstanceState)
    }
}
