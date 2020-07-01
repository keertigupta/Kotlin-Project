package connect.com.credr.connect.ui.franchiselogistics.coordinator.model

data class Vehicle(
    val cityName: String,
    val makeName: String,
    val modelName: String,
    val orderId: String,
    val registrationNumber: String,
    val requestedDate: String,
    val status: String,
    val storeId: String,
    val storeName: String,
    val variantName: String
)