package com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote

import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.feature.moviesearch.data.datasource.MovieSearchingDataSource
import com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote.HttpRoutes.API_KEY
import com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote.HttpRoutes.API_KEY_PARAM
import com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote.HttpRoutes.BASE_URL
import com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote.HttpRoutes.SEARCH_PARAM
import com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote.dto.MovieResponse
import com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote.dto.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.IOException

class NetworkMovieSearchingDataSource(
    private val client: HttpClient
) : MovieSearchingDataSource {
    override suspend fun getMovies(query: String): Resource<List<MovieResponse>> {
        return try {
            val response: HttpResponse = client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    parameters.append(API_KEY_PARAM, API_KEY)
                    parameters.append(SEARCH_PARAM, query)
                }
            }
            val jsonBody = response.bodyAsText()
            val json = Json { ignoreUnknownKeys = true }
            val movieResponse = json.decodeFromString(SearchResponse.serializer(), jsonBody)
            Resource.Success(movieResponse.movies)
        } catch (e: SerializationException) {
            Resource.Success(emptyList())
        } catch (e: IOException) {
            Resource.Error(e.message)
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}

