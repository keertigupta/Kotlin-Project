package connect.com.credr.connect.common

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.JsonObject
import connect.com.credr.connect.R
import connect.com.credr.connect.database.PreferenceManager
import connect.com.credr.connect.services.FranchiseWebServiceProvider
import connect.com.credr.connect.services.WebServiceProvider
import connect.com.credr.connect.ui.franchiselogistics.genericpractice.FranchiseAssignRunnersActivityGeni
import connect.com.credr.connect.ui.franchiselogistics.coordinator.model.FranchRunnersList
import connect.com.credr.connect.ui.franchiselogistics.coordinator.model.Runner
import connect.com.credr.connect.ui.franchiselogistics.coordinator.model.SendAssignRunner
import connect.com.credr.connect.ui.logistics.assign.model.CoordinatorLead
import connect.com.credr.connect.ui.logistics.assign.model.RunnerList
import connect.com.credr.connect.ui.logistics.assign.model.RunnerListResponseBean
import connect.com.credr.connect.ui.logistics.assign.model.UpdateRunnerResponseBean
import connect.com.credr.connect.utils.Utils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.dialog_assign.view.*
import java.util.*


class BottomDialog: BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener{

    companion object{
        val ARG ="POSITION"
        val ARG1 = "ORDER_ID"
        fun newInstance(pos:Int,orderId:String):BottomDialog{
            val b  = Bundle()
            b.putInt(ARG,pos)
            b.putString(ARG1,orderId)
            val  dfrag = BottomDialog()
            dfrag.arguments = b
            return dfrag

        }
    }


    private lateinit var orderId: String
    private  var itempos: Int=0
    private var provider = WebServiceProvider.retrofit.create(WebServiceProvider::class.java)
    private var franchiseprovider = FranchiseWebServiceProvider.retrofit.create(FranchiseWebServiceProvider::class.java)

    private lateinit var pbRunner: ProgressBar
    private lateinit var spRunner: Spinner
    private lateinit var listener:UpdateCallBack

    var bean: CoordinatorLead?=null

    var runnerList: MutableList<RunnerList>? =null


    private lateinit  var runnerListForFranchise: List<Runner>

    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = FranchiseAssignRunnersActivityGeni()
        } catch (e:ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement FeedbackListener")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  if (getArguments() != null) {
            val mArgs = arguments
            var myDay= mArgs.getString("title")
        }*/
       /* arguments.let {
            //getInt(ARG)!!
        }*/
        itempos = arguments?.getInt(ARG)!!
        orderId = arguments?.getString(ARG1)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {

        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.dialog_assign, null)
        dialog.setContentView(contentView)
        val params = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior
        if (behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(bottomSheetCallback)
            contentView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    contentView.viewTreeObserver.removeGlobalOnLayoutListener(this)
                    val height = contentView.measuredHeight
                    (behavior as BottomSheetBehavior).peekHeight = height
                }
            })
        }


        pbRunner = contentView.pbRunner

        spRunner = contentView.spRunner
        getRunnersListForfranchise()
       // getRunnersListGeneric()
/*
        if(comeFrom.equals("FRANCHISE")){
            getRunnersListForfranchise()
        }else{
            getRunnersListApi()
        }*/
        contentView.btnSubmit.setOnClickListener {

                if (spRunner.selectedItemPosition == 0) {
                    Utils.toast("Please select Runner",context!!)
                }else{
                    submitRunnerAPIForFranchise(orderId,listener)
                }
              /*  else if (comeFrom == "FRANCHISE"){
                    submitRunnerAPIForFranchise(beanAssign.orderId,listener)
                }
                else {
                    updateRunnerAPI(bean!!.leadId,listener)
                }*/


        }

    }

   /* abstract fun getRunnersList()

    private fun <T>getRunnersListGeneric(data:T) {

    }
*/


    private fun getRunnersListApi() {
        pbRunner.visibility= View.VISIBLE
        provider!!.getRunnerList(bean!!.leadId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<RunnerListResponseBean> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: RunnerListResponseBean) {
                        Utils.hideDialog()
                        pbRunner.visibility= View.GONE

                        if (t.isIsValid) {
                            updateRunnerList(t.runnerList!!)
                        } else {
                            Utils.toast(context!!.getString(R.string.something_went_wrong),context)
                        }

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Utils.hideDialog()
                        Utils.toast(context!!.getString(R.string.something_went_wrong), context)
                    }
                })

    }

    private fun getRunnersListForfranchise() {
        pbRunner.visibility= View.VISIBLE
        franchiseprovider!!.getRunnerList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<FranchRunnersList> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: FranchRunnersList) {
                        Utils.hideDialog()
                        pbRunner.visibility= View.GONE

                        if (t.valid) {
                            updateRunnerListForFranch(t.runners)
                        } else {
                            Utils.toast(context!!.getString(R.string.something_went_wrong),context)
                        }

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Utils.hideDialog()
                        Utils.toast(context!!.getString(R.string.something_went_wrong), context)
                    }
                })

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spRunner -> {
                if (position != 0) {
                }
            }

        }
    }

    fun updateRunnerListForFranch(data: List<Runner>) {
        runnerListForFranchise=data
        val outlet: MutableList<String> = ArrayList()
        outlet.add("Select")
        for (item in data)
            outlet.add(item.name)
        val adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item,outlet )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRunner.adapter = adapter
        spRunner.onItemSelectedListener = this
    }


    fun updateRunnerList(data: MutableList<RunnerList>) {
        runnerList=data
        val outlet: MutableList<String> = ArrayList()
        outlet.add("Select")
        for (item in data)
            //outlet.add(item?.userName)
            outlet.add(item.userName!!)
        val adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item,outlet )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRunner.adapter = adapter
        spRunner.onItemSelectedListener = this
    }


    private fun updateRunnerAPI(leadId:String,listener:UpdateCallBack?) {
        Utils.showDialog(context!!,"Assign Runner")

        val user = JsonObject()
        user.addProperty("leadId", leadId)
        user.addProperty("userId", runnerList!![spRunner.selectedItemPosition-1].userId.toString())
        user.addProperty("coordinatorId", PreferenceManager.instance(context).get(PreferenceManager.USER_ID,null))


        provider!!.assignRunner(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<UpdateRunnerResponseBean> {
                    override fun onSubscribe(d: Disposable) {

                    }
                    override fun onSuccess(t: UpdateRunnerResponseBean) {
                        Utils.hideDialog()
                        if(t.isIsValid && !TextUtils.isEmpty(t.message)){
                            Utils.toast(t.message, context)
                            dismiss()
                            listener!!.refreshPage(itempos)
                        }
                        else if (!TextUtils.isEmpty(t.message)){
                            Utils.toast(t.message, context)
                        }
                        else{
                            Utils.toast(context!!.getString(R.string.something_went_wrong), context)
                        }

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Utils.hideDialog()
                        Utils.toast(context!!.getString(R.string.something_went_wrong), context)
                    }
                })

    }



    private fun submitRunnerAPIForFranchise(leadId:String,listener:UpdateCallBack?) {
        Utils.showDialog(context!!,"Assign Runner")

        val user = JsonObject()
        user.addProperty("coordinatorId", PreferenceManager.instance(context).get(PreferenceManager.USER_ID,null))
        user.addProperty("orderId", leadId)
        user.addProperty("runnerID", runnerListForFranchise[spRunner!!.selectedItemPosition-1].id)
        user.addProperty("userId", PreferenceManager.instance(context).get(PreferenceManager.USER_ID,null))

        franchiseprovider!!.assignRunner(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<SendAssignRunner> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: SendAssignRunner) {
                        Utils.hideDialog()

                        if(t.valid){
                            Utils.toast("Runner has been assigned successfully.", context)
                          //  dismiss()
                            dialog.dismiss()
                            listener!!.refreshPage(itempos)
                        }
                        else if (!TextUtils.isEmpty(t.message)){
                            Utils.toast(t.message, context)
                        }
                        else{
                            Utils.toast(context!!.getString(R.string.something_went_wrong), context)
                        }

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Utils.hideDialog()
                        Utils.toast(context!!.getString(R.string.something_went_wrong), context)
                    }
                })

    }

    interface UpdateCallBack {
        fun refreshPage(pos:Int)

    }




}


