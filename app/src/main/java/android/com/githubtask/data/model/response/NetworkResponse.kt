package android.com.githubtask.data.model.response

sealed class NetworkResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()
    data class Failure(val throwable: Throwable) : NetworkResponse<Nothing>()
    data class Error(val error: ErrorResponse) : NetworkResponse<Nothing>()
    data class Loading(val state: Boolean) : NetworkResponse<Nothing>()
}