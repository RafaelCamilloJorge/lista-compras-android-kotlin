class ShoppingItem(
    var id: Int,
    var name: String,
    var image: Int,
    var quantity: Int = 0,
    var unity: UnitOfMeasure,
    var category: Category
)