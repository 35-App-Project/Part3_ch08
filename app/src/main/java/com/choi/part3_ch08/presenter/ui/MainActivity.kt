package com.choi.part3_ch08.presenter.ui

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.choi.part3_ch08.databinding.ActivityMainBinding
import com.choi.part3_ch08.domain.model.Content
import com.choi.part3_ch08.presenter.ui.list.ListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter : ListAdapter by lazy { ListAdapter(Handler()) }

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
    }

    // 새로 작성
    fun onClickAdd() {
        InputActivity.start(this)
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

                }
                .setNegativeButton("아니오") { _, _ ->

                }
                .show()

            return false
        }
    }
}