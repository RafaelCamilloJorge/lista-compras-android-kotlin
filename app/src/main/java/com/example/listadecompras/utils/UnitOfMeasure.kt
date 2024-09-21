enum class UnitOfMeasure {
    kilo,
    gram,
    liter,
    unit;

    fun getName(): String {
        return when (this) {
            kilo -> "Kg"
            gram -> "g"
            liter -> "L"
            unit -> "un"
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