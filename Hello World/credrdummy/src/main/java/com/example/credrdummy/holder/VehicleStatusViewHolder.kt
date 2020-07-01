package connect.com.credr.connect.ui.franchiselogistics.coordinator.adapters.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import connect.com.credr.connect.utils.Constants
import connect.com.credr.connect.R
import connect.com.credr.connect.ui.franchiselogistics.coordinator.model.FranchiseLogisticsRecordX
import connect.com.credr.connect.utils.Utils

/**
 * Created by Keerti on 13-06-2020.
 */

class VehicleStatusViewHolder(itemView: View) : BaseViewHolder<FranchiseLogisticsRecordX>(itemView) {


    override fun bind(item: FranchiseLogisticsRecordX, contex: Context) {
        val bikeName = item.makeName + " " + item.modelName + " " + item.variantName
        val regNo = "(${item.registrationNumber})"

        when (item.vehicleStatus) {
            Constants.DELIVERED_FRAN -> {
                imDeliveryDatelayout.visibility = View.VISIBLE
                tvDeliveryDate.text = item.deliveryDate
                tvStatus.text = "Delivered"
                tvStatus.setTextColor(contex.resources.getColor(R.color.green))

            }
            Constants.INTRANSI_FRAN -> {
                tvStatus.setTextColor(contex.resources.getColor(R.color.blue))
                tvStatus.text = "In Transit"
            }
            Constants.PENDING_FRAN -> {
                tvStatus.setTextColor(contex.resources.getColor(R.color.orange))
                tvStatus.text = "Pending"
            }
            Constants.PENDING_CONFIRMATION_FRAN -> {
                tvStatus.setTextColor(contex.resources.getColor(R.color.orange))
                tvStatus.text = "Pending Confirmation"
            }
            Constants.DELIVERY_DISPUTE -> {
                tvStatus.setTextColor(contex.resources.getColor(R.color.orange))
                tvStatus.text = "Dispute"
            }
            Constants.ASSIGNED -> {
                tvStatus.setTextColor(contex.resources.getColor(R.color.blue))
                tvStatus.text = "Assigned"
            }
            Constants.PICKUP_DISPUTE -> {
                tvStatus.setTextColor(contex.resources.getColor(R.color.red))
                tvStatus.text = "Dispute"
            }
            else -> {
                tvStatus.setTextColor(contex.resources.getColor(R.color.blue))
                tvStatus.text = item.vehicleStatus
            }
        }

        if (item.runner != null && !item.vehicleStatus.equals(Constants.DELIVERED_FRAN)) {
            call.visibility = View.VISIBLE
            //tvRunnerName.text = "Assigned Runner : ${d.runner.name}"
            call.setOnClickListener {
               // Utils.openDialer(openDialer(contex, item.runner.mobileNumber))
                with(Utils) { openDialer(contex, item.runner.mobileNumber) }
            }
        }
        //var exchangeDate = Utils.getFormattedDate(d.requestedDate)


        //  var deliveryDate1 = Utils.getFormattedDate(d.requestedDate)

        tvBikeName.text = bikeName
        tvBikeNumber.text = regNo
        tvStore.text = item.storeName

        tvExchangeDate.text = item.requestedDate
        if (!item.pickupDate.isNullOrEmpty()) tvPickedupDate.text = item.pickupDate
        //  val  _name= item.runner?.name  // safe call operator is used to check null runner
        val runnerName = contex.getString(R.string.assignrunner) + {item.runner?.name}
        tvRunnerName.text = runnerName
     //   tvRunnerName.text = "Assigned Runner : ${item.runner?.name}"


    }

    var imPickedUpDate: View = itemView.findViewById(R.id.imPickedUpDate)
    var imDeliveryDatelayout: RelativeLayout = itemView.findViewById(R.id.imDeliveryDatelayout)
    var tvBikeName: TextView = itemView.findViewById(R.id.tvBikeName)
    var tvBikeNumber: TextView = itemView.findViewById(R.id.tvBikeNumber)
    var tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    var call: ImageView = itemView.findViewById(R.id.call)
    var tvStore: TextView = itemView.findViewById(R.id.tvStoreName)

    var tvExchangeDate: TextView = itemView.findViewById(R.id.tvExchangeDate)
    var tvPickedupDate: TextView = itemView.findViewById(R.id.tvPickedUpDate)
    var tvDeliveryDate: TextView = itemView.findViewById(R.id.tvDeliveryDate)
    var tvRunnerName: TextView = itemView.findViewById(R.id.tvRunnerName)

    var tvPickedupDateLabel: TextView = itemView.findViewById(R.id.tvPickedUpDateLabel)
    override fun clear() {

    }


}