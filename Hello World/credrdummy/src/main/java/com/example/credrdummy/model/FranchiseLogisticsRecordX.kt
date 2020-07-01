package connect.com.credr.connect.ui.franchiselogistics.coordinator.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FranchiseLogisticsRecordX(
    val assignedDate: String,
    val cityName: String,
    val deliveryDate: String,
    val deliveryTAT: String,
    val disputeReason: String,
    val makeName: String,
    val manufactureYear: Int?,
    val modelName: String,
    val orderId: String,
    val pickupDate: String?,
    val registrationNumber: String,
    val requestedDate: String,
    val runner: RunnerX?,
    val salesExecutive: SalesExecutive,
    val storeId: String,
    val storeName: String,
    val variantName: String,
    val vehicleStatus: String
):Parcelable