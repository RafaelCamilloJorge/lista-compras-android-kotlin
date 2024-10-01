sealed class OnResult<T> {
    data class Success<T>(val data: T) : OnResult<T>()
    data class Error<T>(val exception: CustomError) : OnResult<T>()

    fun <R> fold(
        onSuccess: (T) -> R,
        onError: (CustomError) -> R
    ): R {
        return when (this) {
            is Success -> onSuccess(data)
            is Error -> onError(exception)
            else -> onError(CustomError("Erro de execução"))
        }
    }
}

class CustomError(private var error: String?) : Exception(error) {
    fun messageError(): String {
        return error ?: this.message ?: "Erro desconhecido"
    }
}