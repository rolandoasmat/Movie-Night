package com.asmat.rolando.popularmovies.ui.watchlatermovies

import androidx.lifecycle.ViewModelProviders
import com.asmat.rolando.popularmovies.repositories.MoviesRepository
import com.asmat.rolando.popularmovies.repositories.PeopleRepository
import com.asmat.rolando.popularmovies.ui.common.*
import com.asmat.rolando.popularmovies.viewmodels.ViewModelFactory
import javax.inject.Inject

class WatchLaterMoviesGridFragment : MovieGridFragment() {

    @Inject
    lateinit var moviesRepository: MoviesRepository

    @Inject
    lateinit var peopleRepository: PeopleRepository

    override val viewModel: MovieGridViewModel
        get() = ViewModelProviders.of(this, ViewModelFactory(moviesRepository, peopleRepository)).get(WatchLaterViewModel::class.java)

}