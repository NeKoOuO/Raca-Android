package com.skyd.raca.ui.screen.add

import androidx.lifecycle.viewModelScope
import com.skyd.raca.appContext
import com.skyd.raca.base.BaseViewModel
import com.skyd.raca.base.IUIChange
import com.skyd.raca.model.preference.CurrentArticleUuidPreference
import com.skyd.raca.model.respository.AddRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private var addRepository: AddRepository) :
    BaseViewModel<AddState, AddEvent, AddIntent>() {
    override fun initUiState(): AddState {
        return AddState(
            GetArticleWithTagsUiState.INIT
        )
    }

    override fun IUIChange.checkStateOrEvent() = this as? AddState to this as? AddEvent

    override fun Flow<AddIntent>.handleIntent(): Flow<IUIChange> = merge(
        filterIsInstance<AddIntent.GetArticleWithTags>().flatMapConcat { intent ->
            addRepository.requestGetArticleWithTags(intent.articleUuid)
                .mapToUIChange { data ->
                    copy(getArticleWithTagsUiState = GetArticleWithTagsUiState.SUCCESS(data))
                }
                .defaultFinally()
        },

        filterIsInstance<AddIntent.AddNewArticleWithTags>().flatMapConcat { intent ->
            addRepository.requestAddArticleWithTags(intent.articleWithTags)
                .mapToUIChange { data ->
                    CurrentArticleUuidPreference.put(
                        context = appContext,
                        scope = viewModelScope,
                        value = data
                    )
                    AddEvent(addArticleResultUiEvent = AddArticleResultUiEvent.SUCCESS(data))
                }
                .defaultFinally()
        }
    )
}