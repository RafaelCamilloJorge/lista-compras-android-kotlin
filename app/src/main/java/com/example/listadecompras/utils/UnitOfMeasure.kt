enum class UnitOfMeasure {
    kilo,
    gram,
    liter,
    unit;

    fun getName(): String {
        return when (this) {
            kilo -> "Quilo"
            gram -> "Grama"
            liter -> "Litro"
            unit -> "Unidade"
        }
    }

    fun getIcon(): String {
        return when (this) {
            kilo -> ""
            gram -> ""
            liter -> ""
            unit -> ""
        }
    }
}