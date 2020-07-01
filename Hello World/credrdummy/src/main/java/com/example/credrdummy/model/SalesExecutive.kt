package connect.com.credr.connect.ui.franchiselogistics.coordinator.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SalesExecutive(
    val id: String,
    val mobileNumber: String,
    val name: String
): Parcelable