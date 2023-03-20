package com.dream.realinterviewquestion.mvi

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.dream.realinterviewquestion.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

class MVIActivity : AppCompatActivity() {


    private val etUserName by lazy {
        findViewById<EditText>(R.id.et_user_name)
    }

    private val etPwd by lazy {
        findViewById<EditText>(R.id.et_pwd)
    }

    private val btnLogin by lazy {
        findViewById<EditText>(R.id.btn_login)
    }

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mviactivity)

        initView()
        initViewStates()
        initViewEvents()
    }

    private fun initView() {
        etUserName.addTextChangedListener {
            viewModel.dispatch(LoginViewAction.UpdateUserName(it.toString()))
        }

        etPwd.addTextChangedListener {
            viewModel.dispatch(LoginViewAction.UpdatePassword(it.toString()))
        }

        btnLogin.setOnClickListener {
            viewModel.dispatch(LoginViewAction.Login)
        }
    }

    private fun initViewStates() {
        viewModel.viewStates.let { states ->
            lifecycleScope.launch {
                states.collect{

                }
            }
        }
    }

    private fun initViewEvents() {
        lifecycleScope.launch {
            viewModel.viewEvents.collect {
                when (it) {
                    is LoginViewEvent.ShowToast -> Toast.makeText(
                        this@MVIActivity,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    is LoginViewEvent.ShowLoadingDialog -> showLoadingDialog()
                    is LoginViewEvent.DismissLoadingDialog -> dismissLoadingDialog()

                }
            }
        }
    }


    private fun dismissLoadingDialog() {
        progressDialog.dismiss()
    }

    @Suppress("DEPRECATION")
    private fun showLoadingDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("加载中...")
        progressDialog.show()
    }

}