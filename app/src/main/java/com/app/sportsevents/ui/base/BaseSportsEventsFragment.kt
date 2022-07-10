package com.app.sportsevents.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.sportsevents.navigation.NavigationCommand
import com.app.sportsevents.BR

abstract class BaseSportsEventsFragment<VM : BaseSportsEventsViewModel, VDB : ViewDataBinding> :
    Fragment() {

    protected lateinit var binding: VDB
    protected val viewModel by lazy { createViewModel() }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        viewModel.uiCommands.observe(viewLifecycleOwner, Observer(::onUiCommands))
        viewModel.navigationCommands.observe(viewLifecycleOwner, Observer(::onNavigate))
    }

    protected abstract fun createViewModel(): VM

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected fun showProgressBar(show: Boolean) {
        val activity = requireActivity()
        if (activity is MainActivity) activity.showProgressBar(show)
    }

    @CallSuper
    protected open fun onUiCommands(command: Any) {
        when (command) {
            is ShowProgress -> showProgressBar(command.show)
        }
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        showProgressBar(false)
    }

    private fun onNavigate(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navigationCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }
}