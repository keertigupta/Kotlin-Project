package connect.com.credr.connect.ui.franchiselogistics.coordinator.model

data class FranchiseVehicleStatus(
        val franchiseLogisticsRecords: List<FranchiseLogisticsRecord>,
        val message: String,
        val valid: Boolean,
        val isValid: Boolean

)