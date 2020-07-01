package connect.com.credr.connect.ui.franchiselogistics.coordinator.model

data class AssignRunnerResponse(
    val message: Any,
    val valid: Boolean,
    val vehicleList: List<Vehicle>
)