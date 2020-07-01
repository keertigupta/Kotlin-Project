package com.example.credrdummy.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.credrdummy.R
import com.example.credrdummy.common.GenericAdapter
import com.example.credrdummy.service.FranchiseWebServiceProvider
import com.google.gson.JsonObject
import com.example.credrdummy.holder.AssignRunnerHolder
import com.example.credrdummy.holder.BaseViewHolder
import com.example.credrdummy.holder.ListUpdateListener
import com.example.credrdummy.model.AssignRunnerResponse
import com.example.credrdummy.model.Vehicle
import com.example.credrdummy.util.Utils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_franchise_assign_runners.*
import kotlinx.android.synthetic.main.activity_franchise_assign_runners.llNetworkError
import kotlinx.android.synthetic.main.activity_franchise_assign_runners.progressBar
import kotlinx.android.synthetic.main.activity_franchise_assign_runners.toolbar
import kotlinx.android.synthetic.main.activity_franchise_assign_runners.tvEmpty
import kotlinx.android.synthetic.main.layout_network_error.*



class FranchiseAssignRunnersActivity : AppCompatActivity(){

    private lateinit var searchView: SearchView
    private lateinit var assignRunnerAdapter: GenericAdapter<Any>
    private var mDataList = ArrayList<Vehicle>()
    private var index:Int=1
    private var loading:Boolean=false
    private var apiProvider: FranchiseWebServiceProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_franchise_assign_runners)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Assign Runner"
        apiProvider = FranchiseWebServiceProvider.retrofit.create(FranchiseWebServiceProvider::class.java)
        setRecyclerViewScrollListener()

        btnRetry.setOnClickListener {
            llNetworkError.visibility = View.GONE
            fetchBikeList()
        }

    }

    override fun onResume() {
        super.onResume()
        index=1
        fetchBikeList()

    }


    private fun updateEmptyData() {
        if (!loading && index==1) {
            tvEmpty.visibility = View.VISIBLE
            rv_assignrunner.visibility = View.GONE
        }
        else{
            loading = false
        }
    }

    fun fetchBikeList() {
        progressBar.visibility = View.VISIBLE
        val user = createRequestBody()
        apiProvider?.getVehicleForCoordinator(user)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<AssignRunnerResponse> {
                    override fun onSuccess(r: AssignRunnerResponse) {
                        progressBar.visibility = View.GONE
                        mDataList = ArrayList(r.vehicleList)
                        //mDataList.addAll(r.vehicleList)
                        setListAdapters(mDataList)
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(">>>>>>","onSubscribe")
                    }

                    override fun onError(e: Throwable) {
                        Log.d(">>>onError",e.message)
                        llNetworkError.visibility = View.VISIBLE
                        e.printStackTrace()
                        progressBar.visibility = View.GONE


                    }

                })
    }

    private fun createRequestBody(): JsonObject {
        val user = JsonObject()
        user.addProperty("cityId", 1)
        user.addProperty("pageNumber", index)
        user.addProperty("deviceType", "MOBILE")
        return user
    }


    private fun setListAdapters(list:List<Vehicle>) {
        if (!loading && index==1) {
            if (list.isNotEmpty()) {
                rv_assignrunner.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                rv_assignrunner.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
                assignRunnerAdapter =object: GenericAdapter<Any>(mDataList.toMutableList(),this@FranchiseAssignRunnersActivity){

                    override fun getLayoutId(position: Int, obj: Any): Int {
                        return R.layout.recyclerview_item_row
                    }

                    @Suppress("UNCHECKED_CAST")
                    override fun getViewHolder(view: View, viewType: Int): BaseViewHolder<Any> {




                        return  AssignRunnerHolder(view, object : ListUpdateListener {
                            override fun onUpdateList(pos: Int) {
                                assignRunnerAdapter.updateList(pos)
                                mDataList.removeAt(pos)
                                if(assignRunnerAdapter.itemCount==0){
                                    tvEmpty.visibility = View.VISIBLE
                                }
                            }

                        }) as BaseViewHolder<Any>                  }

                }

                rv_assignrunner.adapter = assignRunnerAdapter
                rv_assignrunner.visibility = View.VISIBLE
            } else {
                tvEmpty.visibility = View.VISIBLE
                rv_assignrunner.visibility = View.GONE

            }
        }else{
            mDataList.addAll(list)
            assignRunnerAdapter.changeList(list.toMutableList())
            loading = false
        }


    }

    private fun setRecyclerViewScrollListener() {
        rv_assignrunner.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val totalItemCount = recyclerView.layoutManager!!.itemCount

                val lastCompletelyVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val count = 10
                if (totalItemCount% count ==0 && totalItemCount >= count &&!loading && totalItemCount <= lastCompletelyVisibleItemPosition + 3) {
                    loading = true
                    index=(totalItemCount/ count)+1
                    fetchBikeList()
                }
                else if (totalItemCount% count !=0 && totalItemCount >= count &&!loading && totalItemCount <= lastCompletelyVisibleItemPosition + 3){
                    Utils.toast("No more results found", this@FranchiseAssignRunnersActivity)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.searchoption_menu, menu )

        val searchItem: MenuItem = menu?.findItem(R.id.action_search) as MenuItem
        searchView = searchItem.actionView as SearchView

        /*  val clearBtn  = searchView.findViewById<ImageView>(R.id.btnClearSearch)
         clearBtn.setOnClickListener{
             Utils.toast("Button clicked",this)
         }*/


        searchView.maxWidth =Int.MAX_VALUE

        searchName(searchView)

        return true
    }

    private fun searchName(searchView: SearchView) {
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //  assignRunnerAdapter.filter.filter(newText)
                beginSearch(newText!!)
                return true
            }

        })

    }


    fun beginSearch(query: String) {
        val newList = java.util.ArrayList<Vehicle>()
        if(mDataList.size>0) {
            if (!TextUtils.isEmpty(query)) {
                for (bike in mDataList) {
                    if (bike.registrationNumber.contains(query, true)
                            || bike.makeName.contains(query, true)
                            || bike.modelName.contains(query, true)) {
                        newList.add(bike)
                    }
                }
                if (newList.isNotEmpty()) {
                    assignRunnerAdapter.changeList(newList.toMutableList())
                    // tvEmpty.visibility = View.GONE
                    //rv_assignrunner.visibility = View.VISIBLE
                } else {
                    tvEmpty.text = "No match found"
                    tvEmpty.visibility = View.VISIBLE
                    rv_assignrunner.visibility = View.GONE
                }
            } else {
                /* tvEmpty.visibility = View.GONE
                 rv_assignrunner.visibility = View.VISIBLE
                 assignRunnerAdapter.changeList(mDataList.toMutableList())*/

            }
        }else{
            Utils.toast("There is no list to search.",this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home)
            onBackPressed()
        if(id == R.id.action_search){
            return  true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        if(!searchView.isIconified){
            searchView.isIconified = true
        }
        super.onBackPressed()
    }

}
