package com.example.credrdummy.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.credrdummy.R
import com.example.credrdummy.model.FranchiseLogisticsRecordX
import com.example.credrdummy.model.VehicleStatusResponseFran
import com.example.credrdummy.service.FranchiseWebServiceProvider
import com.example.credrdummy.util.Constants
import com.google.gson.JsonObject

import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_vehicle_status.*
import kotlinx.android.synthetic.main.layout_network_error.*
import java.util.*


class FranchiseVehicleStatusActivity : AppCompatActivity() {


    private lateinit var searchView: SearchView
    private var index:Int=1
    private var loading:Boolean=false
    private lateinit var apiProvider: FranchiseWebServiceProvider
    private var mDataList = ArrayList<FranchiseLogisticsRecordX>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_status)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Vehicle Status"
        //setRecyclerViewScrollListener()
        apiProvider = FranchiseWebServiceProvider.retrofit.create(FranchiseWebServiceProvider::class.java)
        viewPager.adapter = PagerAdapter(supportFragmentManager,mDataList)
        tabLayout.setupWithViewPager(viewPager)
        btnRetry.setOnClickListener {
            llNetworkError.visibility = View.GONE
            fetchBikeList()
        }

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                Log.d(">>>>>", "my position is : $position")
              //  PreferenceManager.instance(this@FranchiseVehicleStatusActivity).currentItem = position

            }

            override fun onPageScrollStateChanged(state: Int) {}
        })


    }
    override fun onResume() {
        super.onResume()

     //   val selectedTab  = PreferenceManager.instance(this@FranchiseVehicleStatusActivity).currentItem
       // viewPager.currentItem = selectedTab
        index=1
        fetchBikeList()

    }

    private fun filerList(list:ArrayList<FranchiseLogisticsRecordX>, status:String):ArrayList<FranchiseLogisticsRecordX>{
        val listFilter = ArrayList<FranchiseLogisticsRecordX>()
        for(l in list){
            if(l.vehicleStatus == status)
                listFilter.add(l)
        }
        Log.d(">>status",status)
        Log.d(">>status count ",listFilter.size.toString())
        return listFilter
    }

    private fun fetchBikeList() {


        progressBar.visibility = View.VISIBLE

        val user = createRequestBody()



        apiProvider.getVehicleStatus(user).observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<VehicleStatusResponseFran> {
                    override fun onSuccess(r: VehicleStatusResponseFran) {

                        Log.d("Ok>>>","onSuccess")
                        progressBar.visibility = View.GONE
                        mDataList.clear()
                        mDataList.addAll(r.franchiseLogisticsRecords)

                        viewPager.adapter = PagerAdapter(supportFragmentManager,mDataList)
                        tabLayout.setupWithViewPager(viewPager)


                    }
                    override fun onSubscribe(d: Disposable) {

                    }
                    override fun onError(e: Throwable) {

                        Log.d("Ok>>>","OnError")

                            llNetworkError.visibility = View.VISIBLE
                            e.printStackTrace()
                            progressBar.visibility = View.GONE


                    }

                })

    }

    private fun createRequestBody(): JsonObject {
        val user = JsonObject()

        //  user.addProperty("coordinatorId", PreferenceManager.instance(context).get(PreferenceManager.USER_ID,null))
        user.addProperty("vehicleStatus", Constants.ALL)
        user.addProperty("cityId",1)
        user.addProperty("deviceType", "MOBILE")
        user.addProperty("requestType","runner")

        return user
    }

   /* private fun setRecyclerViewScrollListener() {
        rv_assignrunner.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val totalItemCount = recyclerView.layoutManager!!.itemCount
                var lastCompletelyVisibleItemPosition = 0

                lastCompletelyVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                Logger.d("setRecyclerViewScrollListener",totalItemCount.toString()+"totalItemCount<---lastCompletelyVisibleItemPosition-->"+lastCompletelyVisibleItemPosition)
                val count = 10
                if (totalItemCount% count ==0 && totalItemCount >= count &&!loading && totalItemCount <= lastCompletelyVisibleItemPosition + 3) {
                    loading = true
                    index=(totalItemCount/ count)+1
                    fetchBikeList()
                }
                else if (totalItemCount% count !=0 && totalItemCount >= count &&!loading && totalItemCount <= lastCompletelyVisibleItemPosition + 3){
                    Utils.toast("no results found", this@FranchiseVehicleStatusActivity)
                }
            }
        })
    }*/


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.searchoption_menu, menu)

        val mSearchMenuItem: MenuItem = menu.findItem(R.id.action_search)
        searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView = mSearchMenuItem.actionView as SearchView

        searchView.setIconifiedByDefault(true)
        MenuItemCompat.expandActionView(mSearchMenuItem)
        val queryTextListener: SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(query: String?): Boolean { // this is your adapter that will be filtered
                val pagerAdapter = viewPager.adapter
                for (i in 0 until pagerAdapter!!.count) {
                    val viewPagerFragment = viewPager.adapter!!.instantiateItem(viewPager, i) as Fragment
                    if ( viewPagerFragment.isAdded) {
                        if (viewPagerFragment is FragmentVehicleListStatus) {
                            viewPagerFragment.beginSearch(query!!)
                            /*allListFragment = viewPagerFragment as AllVehicleListFragment
                            if (allListFragment != null) {
                                allListFragment.beginSearch(query)
                            }*/
                        } /*else if (viewPagerFragment is SystemDefaultAppFragment) {
                            groupsFragment = viewPagerFragment as SystemDefaultAppFragment
                            if (groupsFragment != null) {
                                groupsFragment.beginSearch(query)
                            }
                        }*/
                    }
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean { // Here u can get the value "query" which is entered in the
                return false
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    inner class PagerAdapter(fm: FragmentManager,var list:ArrayList<FranchiseLogisticsRecordX>) : FragmentPagerAdapter(fm) {

        private val mTabArray = arrayOf("All", "Assigned", "Pending", "In Transit", "Delivered","Dispute")

        private  val franArray = arrayOf(
            FragmentVehicleListStatus.newInstance(
                list
            ),
            FragmentVehicleListStatus.newInstance(
                filerList(
                    list,
                    Constants.ASSIGNED_FRAN
                )
            ),
            FragmentVehicleListStatus.newInstance(
                filerList(
                    list,
                    Constants.PENDING_FRAN
                )
            ),
            FragmentVehicleListStatus.newInstance(
                filerList(
                    list,
                    Constants.INTRANSI_FRAN
                )
            ),
            FragmentVehicleListStatus.newInstance(
                filerList(
                    list,
                    Constants.DELIVERED_FRAN
                )
            ),
            FragmentVehicleListStatus.newInstance(
                filerList(
                    list,
                    Constants.PICKUP_DISPUTE
                )
            )
        )

        override fun getItem(position: Int): Fragment {

            return franArray[position]

        }

        override fun getCount(): Int {
            return mTabArray.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mTabArray[position]
        }

    }
}
