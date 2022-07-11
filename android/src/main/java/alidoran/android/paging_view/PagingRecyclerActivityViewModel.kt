package alidoran.android.paging_view

import alidoran.android.paging_view.network.CharacterData
import alidoran.android.paging_view.network.RetroInstance
import alidoran.android.paging_view.network.RetroService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class PagingRecyclerActivityViewModel: ViewModel() {

    var retroService: RetroService = RetroInstance.getRetroInstance().create(RetroService::class.java)

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
        pagingSourceFactory = { CharacterPagingSource(retroService) })
            .flow
            .cachedIn(viewModelScope)
    }
}