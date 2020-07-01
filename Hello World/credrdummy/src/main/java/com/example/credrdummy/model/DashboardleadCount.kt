package connect.com.credr.connect.ui.franchiselogistics.coordinator.model

data class DashboardleadCount(
    val delivered: String,
    val incentive: String?,
    val isValid: Boolean,
    val message: String,
    val pendingPickup: String,
    val totalAssigned: String
)