package com.sdgagfhsghghghdfg.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

/**
 * В ViewModel мы собираемся открыть Flow<PagingData<Repo>>.
 * Здесь мы избегаем запрашивать то же имя пользователя,
 * если оно было запрошено ранее.
 * Кроме того, мы кэшируем содержимое файла Flow<PagingData>>.
 */
class GithubViewModel(
    private val repository: GithubRepository
) : ViewModel() {

    private var currentUsernameValue: String? = null

    private var currentSearchResult: Flow<PagingData<Repo>>? = null

    fun searchRepos(username: String): Flow<PagingData<Repo>> {
        val lastResult = currentSearchResult
        if (username == currentUsernameValue && lastResult != null) {
            return lastResult
        }
        currentUsernameValue = username
        val newResult = repository.searchRepos(username)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}