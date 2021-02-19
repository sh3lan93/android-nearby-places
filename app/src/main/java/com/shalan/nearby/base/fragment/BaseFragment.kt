package com.shalan.nearby.base.fragment

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.shalan.nearby.base.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass


abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewDataBinding>(
    @LayoutRes layoutId: Int,
    clazz: KClass<ViewModel>,
    vararg viewModelParams: Any? = emptyArray()
) :
    Fragment(layoutId),
    IFragment {

    protected lateinit var binding: Binding

    protected val viewmodel: ViewModel by viewModel(clazz = clazz, parameters = {
        parametersOf(viewModelParams)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewmodel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        viewmodel.startLogic()
        onCreateInit(savedInstanceState)
    }

    fun hideKeyboard() {
        (requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            this.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }
    }
}
