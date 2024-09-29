import java.io.Serializable

enum class UnitOfMeasure : Serializable {
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
}