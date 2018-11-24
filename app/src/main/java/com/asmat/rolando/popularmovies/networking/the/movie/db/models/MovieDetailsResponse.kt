package com.asmat.rolando.popularmovies.networking.the.movie.db.models

data class MovieDetailsResponse(val adult: Boolean,
                                val backdrop_path: String?,
                                val belongs_to_collection: Collection?,
                                val budget: Int,
                                val genres: List<Genre>,
                                val homepage: String?,
                                val id: Int,
                                val imdb_id: String?,
                                val original_language: String,
                                val original_title: String,
                                val overview: String?,
                                val popularity: Double,
                                val poster_path: String?,
                                val production_companies: List<ProductionCompany>,
                                val production_countries: List<ProductionCountry>,
                                val release_date: String,
                                val revenue: Int,
                                val runtime: Int?,
                                val spoken_languages: List<SpokenLanguage>,
                                val status: String, // Allowed values: Rumored, Planned, In Production, Post Production, Released, Canceled
                                val tagline: String?,
                                val title: String,
                                val video: Boolean,
                                val vote_average: Double,
                                val vote_count: Int) {

    data class Collection(val id: Int,
                          val name: String,
                          val poster_path: String?,
                          val backdrop_path: String)

    data class Genre(val id: Int,
                     val name: String)

    data class ProductionCompany(val name: String,
                                 val id: Int,
                                 val logo_path: String?,
                                 val origin_country: String)

    data class ProductionCountry(val iso_3166_1: String,
                                 val name: String)

    data class SpokenLanguage(val iso_639_1: String,
                              val name: String)

}