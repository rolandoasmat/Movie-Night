package com.asmat.rolando.popularmovies.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.asmat.rolando.popularmovies.MovieNightApplication
import com.asmat.rolando.popularmovies.R
import com.asmat.rolando.popularmovies.extensions.setNearBottomScrollListener
import com.asmat.rolando.popularmovies.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment: Fragment(), SearchAdapter.Callbacks {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private var adapter: SearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? MovieNightApplication)?.component()?.inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        searchView?.setIconifiedByDefault(false)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setSearchTerm(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchTerm(newText ?: "")
                return false
            }
        })
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search_movies -> {
                viewModel.setSearchMode(SearchViewModel.SearchMode.MOVIES)
            }
            R.id.search_people -> {
                viewModel.setSearchMode(SearchViewModel.SearchMode.PEOPLE)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        adapter = SearchAdapter(this)
        searchResultsRecyclerView?.adapter = adapter
        searchResultsRecyclerView?.layoutManager = GridLayoutManager(requireContext(), 2)
        searchResultsRecyclerView?.setNearBottomScrollListener {
            viewModel.loadMore()
        }
    }

    private fun observeViewModel() {
        viewModel.results.observe(viewLifecycleOwner, Observer {
            updateResults(it)
        })
        viewModel.searchHint.observe(viewLifecycleOwner, Observer {
            searchView?.queryHint = it
        })
    }

    private fun updateResults(items: List<SearchViewModel.SearchResultUiModel>) {
        adapter?.setData(items)
    }

    override fun openMovieDetails(id: Int) {
        val action = SearchFragmentDirections.actionGlobalActionToMovieDetailsScreen(id)
        findNavController().navigate(action)
    }

    override fun openActorDetails(id: Int) {
//        val intent = CastDetailsActivity.createIntent(requireContext(), id)
//        startActivity(intent)
    }
}