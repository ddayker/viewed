package com.dayker.viewed.watched.common.data.datasource.moviesearching.remote

import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.common.data.datasource.moviesearching.MovieSearchingDataSource
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.HttpRoutes.API_KEY
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.HttpRoutes.API_KEY_PARAM
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.HttpRoutes.BASE_URL
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.HttpRoutes.ID_PARAM
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.HttpRoutes.SEARCH_PARAM
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.HttpRoutes.TYPE_MOVIE
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.HttpRoutes.TYPE_PARAM
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.dto.MovieInfo
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.dto.MovieResponse
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.dto.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

internal class NetworkMovieSearchingDataSource(
    private val client: HttpClient
) : MovieSearchingDataSource {
    override suspend fun getMovies(query: String): Resource<List<MovieResponse>> {
        return try {
            val response: HttpResponse = client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    parameters.append(API_KEY_PARAM, API_KEY)
                    parameters.append(TYPE_PARAM, TYPE_MOVIE)
                    parameters.append(SEARCH_PARAM, query.trim())
                }
            }
            val jsonBody = response.bodyAsText()
            val json = Json { ignoreUnknownKeys = true }
            val movieResponse = json.decodeFromString(SearchResponse.serializer(), jsonBody)
            Resource.Success(movieResponse.movies)
        } catch (e: SerializationException) {
            Resource.Success(emptyList())
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

    override suspend fun getMovieInfo(id: String): Resource<MovieInfo> {
        return try {
            val response: HttpResponse = client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                    parameters.append(API_KEY_PARAM, API_KEY)
                    parameters.append(ID_PARAM, id)
                }
            }
            val jsonBody = response.bodyAsText()
            val json = Json { ignoreUnknownKeys = true }
            val movieResponse = json.decodeFromString(MovieInfo.serializer(), jsonBody)
            Resource.Success(movieResponse)
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}

