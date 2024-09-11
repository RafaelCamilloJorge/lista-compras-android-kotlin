class ShoppingItem(
    var name: String,
    var quatity: Int = 0,
    var unity: UnitOfMeasure,
) {
    public getName(): String
    {
        return name;
    }

    public getQuantity():Int
    {
        return quatity;
    }

    public getUnit() : UnitOfMeasure
    {
        return unity;
    }
}