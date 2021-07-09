package com.sdgagfhsghghghdfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sdgagfhsghghghdfg.databinding.ActivityMainBinding
import com.sdgagfhsghghghdfg.paging.GithubViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var searchJob: Job? = null
    private lateinit var viewModel: GithubViewModel
    private lateinit var bindingMain : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        viewModel = ViewModelProvider(this).get(GithubViewModel::class.java)

    }



    private fun search(username: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepos(username).collect { adapter.submitData(it) }
        }
    }
}