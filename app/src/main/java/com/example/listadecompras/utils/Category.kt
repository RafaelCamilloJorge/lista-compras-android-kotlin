enum class Category {
    vegetables,
    meat,
    dairy,
    fish,
    seeds;

    fun getIcon(): String {
        return when (this) {
            vegetables -> ""
            meat -> ""
            dairy -> ""
            fish -> ""
            seeds -> ""
        }
    }
}