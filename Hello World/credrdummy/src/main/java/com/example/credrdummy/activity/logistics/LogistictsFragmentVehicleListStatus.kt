package com.example.credrdummy.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.credrdummy.R
import com.example.credrdummy.holder.BaseViewHolder
import com.example.credrdummy.holder.VehicleStatusViewHolder
import com.example.credrdummy.common.GenericAdapter
import com.example.credrdummy.model.FranchiseLogisticsRecordX
import com.example.credrdummy.model.VehicleStatu
import com.example.credrdummy.model.VehicleStatus
import kotlinx.android.synthetic.main.fragment_all_vehicle_status.*


class LogistictsFragmentVehicleListStatus : Fragment() {

    private lateinit var allListAdapter: GenericAdapter<Any>
    private val TAG = LogistictsFragmentVehicleListStatus::class.java.simpleName
    private var mDataList = ArrayList<VehicleStatu> ()
   companion object {
       val ARG_LIST = "list"
        fun newInstance(myList : ArrayList<VehicleStatu>): LogistictsFragmentVehicleListStatus {
            val args = Bundle()
            args.put(ARG_LIST,myList)
            val fragment =
                LogistictsFragmentVehicleListStatus()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDataList = arguments!!.getSerializable(ARG_LIST)!!
        allListAdapter = object : GenericAdapter<Any>(mDataList.toMutableList(), activity!! as AppCompatActivity) {
            override fun getLayoutId(position: Int, obj: Any): Int {
                return R.layout.item_vehicle_status_all_franch

            }
            @Suppress("UNCHECKED_CAST")
            override fun getViewHolder(view: View, viewType: Int): BaseViewHolder<Any> {
                return VehicleStatusViewHolder(view) as BaseViewHolder<Any>
            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_vehicle_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListAdapters()
    }

    private fun setListAdapters() {
        if(mDataList.isNotEmpty()) {
            rv_vehiclestatus_all.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rv_vehiclestatus_all.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

           // allListAdapter.setItems(mDataList)
            rv_vehiclestatus_all.adapter = allListAdapter
        }else{
            rv_vehiclestatus_all.visibility = View.VISIBLE
            tvEmpty.visibility = View.VISIBLE

        }

    }

    fun beginSearch(query: String) {
        val newList = ArrayList<FranchiseLogisticsRecordX>()
        if (!TextUtils.isEmpty(query)) {
            for (bike in mDataList) {
                if (bike.registrationNumber.contains(query, true)
                        || bike.makeName.contains(query, true)
                        || bike.modelName.contains(query, true)) {
                    newList.add(bike)
                }
            }
            if (newList.isNotEmpty()) {
                allListAdapter.changeList(newList.toMutableList())
                tvEmpty.visibility = View.GONE
                rv_vehiclestatus_all.visibility = View.VISIBLE
            } else {
                tvEmpty.text = "No match found"
                tvEmpty.visibility = View.VISIBLE
                rv_vehiclestatus_all.visibility = View.GONE
            }
        } else {
            tvEmpty.visibility = View.GONE
            rv_vehiclestatus_all.visibility = View.VISIBLE
            allListAdapter.changeList(mDataList.toMutableList())

        }
    }
}