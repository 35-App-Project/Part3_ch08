package com.choi.part3_ch08.presenter.ui

import android.content.Context
import android.content.Intent
import android.icu.util.MeasureUnit.ITEM
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.choi.part3_ch08.databinding.ActivityInputBinding
import com.choi.part3_ch08.domain.model.Content
import com.choi.part3_ch08.presenter.viewmodel.InputViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputActivity : AppCompatActivity() {

    private lateinit var binding :ActivityInputBinding

    private val viewModel : InputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInputBinding.inflate(layoutInflater).apply {
            setContentView(root)
            // LiveData를 관찰 (activity-ktx 를 추가 하는 이유인 듯 싶다)
            lifecycleOwner=this@InputActivity
            viewModel=this@InputActivity.viewModel

        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // item 을 받아 와서 ViewModel 에 전달
        (intent.getSerializableExtra(ITEM) as? Content)?.let {
            viewModel.initData(it)
        }

        observeViewModel()
    }

    // 뒤로 가기 키 눌렀을 때 이전 화면으로
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun observeViewModel() {
        viewModel.doneEvent.observe(this) {
            Toast.makeText(this,it.second, Toast.LENGTH_SHORT).show()
            // 참인 경우 Activity 를 닫는 부분
            if (it.first) {
                finish()
            }
        }
    }

    companion object {
        private const val ITEM="item"

        fun start(context: Context, item: Content?=null) {
            Intent(context,InputActivity::class.java).apply {
                // 데이터 전달을 위함
                putExtra(ITEM,item)
            }.run {
                context.startActivity(this)
            }
        }
    }

}