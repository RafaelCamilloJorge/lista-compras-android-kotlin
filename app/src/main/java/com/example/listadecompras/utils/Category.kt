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

    fun getName(): String {
        return when (this) {
            vegetables -> "Vegetais"
            fish -> "Peixes"
            meat -> "Carnes"
            dairy -> "Laticínio"
            seeds -> "Grãos"
        }
    }
}