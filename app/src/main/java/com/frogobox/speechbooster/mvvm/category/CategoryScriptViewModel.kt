package com.frogobox.speechbooster.mvvm.category

import android.app.Application
import android.content.Context
import com.frogobox.api.movie.ConsumeMovieApi
import com.frogobox.api.news.ConsumeNewsApi
import com.frogobox.coreapi.ConsumeApiResponse
import com.frogobox.coreapi.movie.MovieUrl
import com.frogobox.coreapi.movie.model.TrendingMovie
import com.frogobox.coreapi.movie.response.Trending
import com.frogobox.coreapi.news.NewsConstant
import com.frogobox.coreapi.news.NewsUrl
import com.frogobox.coreapi.news.model.Article
import com.frogobox.coreapi.news.response.ArticleResponse
import com.frogobox.sdk.util.FrogoMutableLiveData
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseViewModel
import com.frogobox.speechbooster.source.FrogoDataRepository
import com.frogobox.speechbooster.source.model.RepositoryScript

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 09/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.viewmodel
 *
 */
class CategoryScriptViewModel(
    application: Application,
    private val frogoDataRepository: FrogoDataRepository
) : BaseViewModel(application) {

    var repository = FrogoMutableLiveData<MutableList<RepositoryScript>>()

    private fun setupNewsData(i: Int, article: Article): RepositoryScript {
        return RepositoryScript("news_$i", article.title, article.description)
    }

    private fun setupMovieData(i: Int, movie: TrendingMovie): RepositoryScript {
        return RepositoryScript("movie_$i", movie.title, movie.overview)
    }

    private fun addMovieData(data: List<TrendingMovie>): MutableList<RepositoryScript> {
        val listRepo = mutableListOf<RepositoryScript>()
        for (i in data.indices) {
            listRepo.add(setupMovieData(i, data[i]))
        }
        return listRepo
    }

    private fun addNewsData(data: List<Article>): MutableList<RepositoryScript> {
        val listRepo = mutableListOf<RepositoryScript>()
        for (i in data.indices) {
            listRepo.add(setupNewsData(i, data[i]))
        }
        return listRepo
    }


    fun showExampleData(context: Context, param: String) {
        val exampleScriptList = mutableListOf<RepositoryScript>()
        exampleScriptList.clear()

        when (param) {
            context.getString(R.string.title_category_religion) -> {
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_0),
                        context.getString(R.string.content_religion_title_0),
                        context.getString(R.string.content_religion_desc_0)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_1),
                        context.getString(R.string.content_religion_title_1),
                        context.getString(R.string.content_religion_desc_1)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_2),
                        context.getString(R.string.content_religion_title_2),
                        context.getString(R.string.content_religion_desc_2)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_3),
                        context.getString(R.string.content_religion_title_3),
                        context.getString(R.string.content_religion_desc_3)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_4),
                        context.getString(R.string.content_religion_title_4),
                        context.getString(R.string.content_religion_desc_4)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_5),
                        context.getString(R.string.content_religion_title_5),
                        context.getString(R.string.content_religion_desc_5)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_6),
                        context.getString(R.string.content_religion_title_6),
                        context.getString(R.string.content_religion_desc_6)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_7),
                        context.getString(R.string.content_religion_title_7),
                        context.getString(R.string.content_religion_desc_7)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_8),
                        context.getString(R.string.content_religion_title_8),
                        context.getString(R.string.content_religion_desc_8)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_religion_id_9),
                        context.getString(R.string.content_religion_title_9),
                        context.getString(R.string.content_religion_desc_9)
                    )
                )
                repository.postValue(exampleScriptList)
            }
            context.getString(R.string.title_category_education) -> {

                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_0),
                        context.getString(R.string.content_education_title_0),
                        context.getString(R.string.content_education_desc_0)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_1),
                        context.getString(R.string.content_education_title_1),
                        context.getString(R.string.content_education_desc_1)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_2),
                        context.getString(R.string.content_education_title_2),
                        context.getString(R.string.content_education_desc_2)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_3),
                        context.getString(R.string.content_education_title_3),
                        context.getString(R.string.content_education_desc_3)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_4),
                        context.getString(R.string.content_education_title_4),
                        context.getString(R.string.content_education_desc_4)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_5),
                        context.getString(R.string.content_education_title_5),
                        context.getString(R.string.content_education_desc_5)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_6),
                        context.getString(R.string.content_education_title_6),
                        context.getString(R.string.content_education_desc_6)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_7),
                        context.getString(R.string.content_education_title_7),
                        context.getString(R.string.content_education_desc_7)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_8),
                        context.getString(R.string.content_education_title_8),
                        context.getString(R.string.content_education_desc_8)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_9),
                        context.getString(R.string.content_education_title_9),
                        context.getString(R.string.content_education_desc_9)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_10),
                        context.getString(R.string.content_education_title_10),
                        context.getString(R.string.content_education_desc_10)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_11),
                        context.getString(R.string.content_education_title_11),
                        context.getString(R.string.content_education_desc_11)
                    )
                )

                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_12),
                        context.getString(R.string.content_education_title_12),
                        context.getString(R.string.content_education_desc_12)
                    )
                )

                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_13),
                        context.getString(R.string.content_education_title_13),
                        context.getString(R.string.content_education_desc_13)
                    )
                )

                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_education_id_14),
                        context.getString(R.string.content_education_title_14),
                        context.getString(R.string.content_education_desc_14)
                    )
                )

                repository.postValue(exampleScriptList)

            }
            context.getString(R.string.title_category_health) -> {

                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_0),
                        context.getString(R.string.content_health_title_0),
                        context.getString(R.string.content_health_desc_0)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_1),
                        context.getString(R.string.content_health_title_1),
                        context.getString(R.string.content_health_desc_1)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_2),
                        context.getString(R.string.content_health_title_2),
                        context.getString(R.string.content_health_desc_2)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_3),
                        context.getString(R.string.content_health_title_3),
                        context.getString(R.string.content_health_desc_3)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_4),
                        context.getString(R.string.content_health_title_4),
                        context.getString(R.string.content_health_desc_4)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_5),
                        context.getString(R.string.content_health_title_5),
                        context.getString(R.string.content_health_desc_5)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_6),
                        context.getString(R.string.content_health_title_6),
                        context.getString(R.string.content_health_desc_6)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_7),
                        context.getString(R.string.content_health_title_7),
                        context.getString(R.string.content_health_desc_7)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_8),
                        context.getString(R.string.content_health_title_8),
                        context.getString(R.string.content_health_desc_8)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_9),
                        context.getString(R.string.content_health_title_9),
                        context.getString(R.string.content_health_desc_9)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_10),
                        context.getString(R.string.content_health_title_10),
                        context.getString(R.string.content_health_desc_10)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_health_id_11),
                        context.getString(R.string.content_health_title_11),
                        context.getString(R.string.content_health_desc_11)
                    )
                )

                repository.postValue(exampleScriptList)

            }
            context.getString(R.string.title_category_general) -> {

                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_0),
                        context.getString(R.string.content_general_title_0),
                        context.getString(R.string.content_general_desc_0)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_1),
                        context.getString(R.string.content_general_title_1),
                        context.getString(R.string.content_general_desc_1)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_2),
                        context.getString(R.string.content_general_title_2),
                        context.getString(R.string.content_general_desc_2)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_3),
                        context.getString(R.string.content_general_title_3),
                        context.getString(R.string.content_general_desc_3)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_4),
                        context.getString(R.string.content_general_title_4),
                        context.getString(R.string.content_general_desc_4)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_5),
                        context.getString(R.string.content_general_title_5),
                        context.getString(R.string.content_general_desc_5)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_6),
                        context.getString(R.string.content_general_title_6),
                        context.getString(R.string.content_general_desc_6)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_7),
                        context.getString(R.string.content_general_title_7),
                        context.getString(R.string.content_general_desc_7)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_8),
                        context.getString(R.string.content_general_title_8),
                        context.getString(R.string.content_general_desc_8)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_9),
                        context.getString(R.string.content_general_title_9),
                        context.getString(R.string.content_general_desc_9)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_10),
                        context.getString(R.string.content_general_title_10),
                        context.getString(R.string.content_general_desc_10)
                    )
                )
                exampleScriptList.add(
                    RepositoryScript(
                        context.getString(R.string.content_general_id_11),
                        context.getString(R.string.content_general_title_11),
                        context.getString(R.string.content_general_desc_11)
                    )
                )

                repository.postValue(exampleScriptList)

            }
            context.getString(R.string.title_category_movie) -> {
                val consumeMovieApi = ConsumeMovieApi(MovieUrl.API_KEY)
                consumeMovieApi.getTrendingMovieDay(object :
                    ConsumeApiResponse<Trending<TrendingMovie>> {
                    override fun onFailed(statusCode: Int, errorMessage: String) {
                        eventFailed.postValue(errorMessage)
                    }

                    override fun onFinish() {

                    }

                    override fun onHideProgress() {
                        eventShowProgress.postValue(false)
                    }

                    override fun onShowProgress() {
                        eventShowProgress.postValue(true)
                    }

                    override fun onSuccess(data: Trending<TrendingMovie>) {
                        repository.postValue(data.results?.let { addMovieData(it) }!!)
                    }
                })
            }
            context.getString(R.string.title_category_news) -> {
                val consumeNewsApi = ConsumeNewsApi(NewsUrl.API_KEY)
                consumeNewsApi.getTopHeadline(
                    null,
                    null,
                    null,
                    NewsConstant.COUNTRY_ID,
                    null,
                    null,
                    object : ConsumeApiResponse<ArticleResponse> {
                        override fun onFailed(statusCode: Int, errorMessage: String) {
                            eventFailed.postValue(errorMessage)
                        }

                        override fun onFinish() {
                        }

                        override fun onHideProgress() {
                            eventShowProgress.postValue(false)
                        }

                        override fun onShowProgress() {
                            eventShowProgress.postValue(true)
                        }

                        override fun onSuccess(data: ArticleResponse) {
                            repository.postValue(data.articles?.let { addNewsData(it) }!!)
                        }
                    })
            }
        }

    }

}