class ShoppingItem(
    var name: String,
    var quatity: Int = 0,
    var unity: UnitOfMeasure,
) {
    public fun getName(): String {
        return name;
    }

    public fun getQuantity(): Int {
        return quatity;
    }

    public fun getUnit(): UnitOfMeasure {
        return unity;
    }
}