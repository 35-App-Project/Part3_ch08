package com.choi.part3_ch08.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.choi.part3_ch08.domain.model.Content
import com.choi.part3_ch08.domain.usecase.ContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val contentUseCase: ContentUseCase
) : ViewModel() {

    private val _doneEvent = MutableLiveData<Pair<Boolean, String>>()
    val doneEvent: LiveData<Pair<Boolean, String>> = _doneEvent

    var category = MutableLiveData("")
    var title = MutableLiveData("")
    var content = MutableLiveData("")

    private var item: Content? = null

    fun initData(item: Content) {
        this.item = item
        category.value = item.category
        title.value = item.title
        content.value = item.content
    }

    fun insertData() {
        val categoryValue = category.value
        val titleValue = title.value
        val contentValue = content.value

        if (category.value.isNullOrBlank() ||
            titleValue.isNullOrBlank() ||
            contentValue.isNullOrBlank()
        ) {
            _doneEvent.value = Pair(false, "모든 항목을 입력해 주세요")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            contentUseCase.save(
                item?.copy(
                    category = categoryValue.toString(),
                    title = titleValue,
                    content = contentValue
                ) ?: Content(
                    category = categoryValue.toString(),
                    title = titleValue,
                    content = contentValue
                )
            ).also {
                _doneEvent.postValue(Pair(true,if(it as Boolean) "완료" else "저장할 수 없음"))
            }
        }
    }


}