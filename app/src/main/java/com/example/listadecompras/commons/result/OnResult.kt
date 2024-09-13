sealed class OnResult<T> {
    data class Success<T>(val data: T) : OnResult<T>()
    data class Error<T>(val exception: CustomError) : OnResult<T>()
}

class CustomError(var error: String?) : Exception(error) {
    fun messageError(): String {
        return error ?: this.message ?: "Erro desconhecido"
    }
}