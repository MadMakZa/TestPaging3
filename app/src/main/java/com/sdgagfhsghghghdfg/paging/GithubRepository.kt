package com.sdgagfhsghghghdfg.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig

/**
 * searchRepos метод, который возвращает Flow<PagingData<Repo>>.
 * В Pager объекте мы определяем GithubRepoPagingSource созданные ранее
 * и PagingConfig с pageSize параметром
 */

class GithubRepository(
    private val api: GithubApi
) {
    fun searchRepos(username: String) = Pager(
        pagingSourceFactory = { GithubRepoPagingSource(api, username) },
        config = PagingConfig(
            pageSize = 20
        )
    ).flow
}