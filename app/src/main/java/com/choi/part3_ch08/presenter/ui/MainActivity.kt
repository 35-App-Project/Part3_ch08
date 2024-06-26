package com.choi.part3_ch08.presenter.ui

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.choi.part3_ch08.databinding.ActivityMainBinding
import com.choi.part3_ch08.domain.model.Content
import com.choi.part3_ch08.presenter.ui.list.ListAdapter
import com.choi.part3_ch08.presenter.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter : ListAdapter by lazy { ListAdapter(Handler()) }

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
            recyclerView.adapter=adapter
            recyclerView.addItemDecoration(
                DividerItemDecoration(this@MainActivity,LinearLayout.VERTICAL)
            )
        }
        observeViewModel()
    }

    // 새로 작성
    fun onClickAdd() {
        InputActivity.start(this)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.contentList
                .flowWithLifecycle(lifecycle,Lifecycle.State.RESUMED)
                .collectLatest {
                    binding.apply {
                        progressBar.isVisible=false
                        emptyTextView.isVisible=it.isEmpty()
                        recyclerView.isVisible=it.isNotEmpty()
                    }
                    adapter.submitList(it)
                }
        }

        viewModel.doneEvent.observe(this) {
            // true 일 때 토스트 메세지
            if (it.first) {
                Toast.makeText(this,it.second,Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 기존 데이터 수정
    inner class Handler {
        fun onClickItem(item: Content) {
            InputActivity.start(this@MainActivity,item)
        }

        fun onLongClickItem(item: Content): Boolean {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("정말 삭제하시겠습니까")
                .setPositiveButton("네") { _, _ ->
                    viewModel.deleteItem(item)
                }
                .setNegativeButton("아니오") { _, _ ->

                }
                .show()

            return false
        }
    }
}