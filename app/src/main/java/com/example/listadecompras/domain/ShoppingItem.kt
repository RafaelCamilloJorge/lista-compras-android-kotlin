import com.example.listadecompras.utils.Category
import com.example.listadecompras.utils.UnitOfMeasure

class ShoppingItem(
    var id: Int,
    var name: String,
    var image: Int,
    var quantity: Int = 0,
    var unity: UnitOfMeasure,
    var category: Category,
    var marked: Boolean = false
)