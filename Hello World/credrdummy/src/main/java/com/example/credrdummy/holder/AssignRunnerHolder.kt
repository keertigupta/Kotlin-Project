package connect.com.credr.connect.ui.franchiselogistics.coordinator.adapters.holder

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import connect.com.credr.connect.R
import connect.com.credr.connect.dialogs.AssignRunnerDialog
import connect.com.credr.connect.ui.franchiselogistics.coordinator.model.Vehicle
import connect.com.credr.connect.utils.Constants

class AssignRunnerHolder (item: View,var listener: ListUpdateListener) : BaseViewHolder<Vehicle>(item){

    private var tvBikeName: TextView = itemView.findViewById(R.id.tv_vehical_name)
    private var tvBikeNumber: TextView = itemView.findViewById(R.id.tvBikeNumber)
    private var tvDate: TextView = itemView.findViewById(R.id.tv_requested_date)

    private var tvStore: TextView = itemView.findViewById(R.id.tv_storename)

    private var btnAssign: TextView = itemView.findViewById(R.id.btnAssign)
    override fun clear() {

    }

    override fun bind(item: Vehicle,contex:Context) {
        tvBikeName.text = item.makeName
        val regNo = "(${item.registrationNumber})"
        tvBikeNumber.text = regNo
        var dateText = tvDate.text.toString()
        var dateTextvalue = item.requestedDate
        tvDate.text = "Request Date:"+dateTextvalue
        tvStore.text = item.storeName

        if(item.status == Constants.PICK_UP_DISPUTE){
            btnAssign.text = "RE ASSIGN RUNNER"
            btnAssign.setTextColor(contex.resources.getColor(R.color.colorPrimary))
            btnAssign.background = contex.resources.getDrawable(R.drawable.border_red__button)
        }

        btnAssign.setOnClickListener {
            //listener.onAssignRunner(adapterPosition)
           AssignRunnerDialog(item, object : AssignRunnerDialog.CallBack {
                override fun refreshPage() {
                    listener.onUpdateList(adapterPosition)
                    // listener.refreshPage()
                }
            }, "FRANCHISE").show((contex as AppCompatActivity).supportFragmentManager, "")
        }

    }
}

interface ListUpdateListener {
    fun onUpdateList(pos:Int)

}
