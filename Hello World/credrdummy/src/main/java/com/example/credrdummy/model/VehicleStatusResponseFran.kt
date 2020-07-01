package connect.com.credr.connect.ui.franchiselogistics.coordinator.model

data class VehicleStatusResponseFran(
    val franchiseLogisticsRecords: List<FranchiseLogisticsRecordX>,
    val isValid: Boolean,
    val message: String,
    val valid: Boolean
)