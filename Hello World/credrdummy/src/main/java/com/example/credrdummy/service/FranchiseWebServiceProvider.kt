package connect.com.credr.connect.services


import com.google.gson.JsonObject
import connect.com.credr.connect.BuildConfig
import connect.com.credr.connect.ui.dashboard.lead_count_pojo.LeadCountPojo
import connect.com.credr.connect.ui.franchise_logistics.francise_runner.franchise_vehicle.runner_documnets_upload.FranchiseVehicleDocList
import connect.com.credr.connect.ui.franchise_logistics.francise_runner.model_franchise.RunnerPendingResponse
import connect.com.credr.connect.ui.franchise_logistics.francise_runner.model_franchise.VehicleStatusResponse
import connect.com.credr.connect.ui.franchiselogistics.coordinator.model.*
import connect.com.credr.connect.ui.logistics.assign.model.UpdateRunnerResponseBean
import connect.com.credr.connect.ui.logistics.documents_update.UpdateRequestBeans
import connect.com.credr.connect.ui.logistics.documents_update.runner_cordinator_docUpload.RunnerDocListResponse
import connect.com.credr.connect.ui.valuator.complete_exchange.sub_activity.DocumentsResponseList
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


/**
 * Created by Shivam Jaiswal on 22/03/18.
 */
interface FranchiseWebServiceProvider {

    companion object {

        private const val LOCAL_BASE_URL="http://192.168.2.76:8080/"//
        private const val PRE_PROD_BASE_URL="http://52.74.127.90:8081/"
        private const val BASE_URL = PRE_PROD_BASE_URL
        private val okHttpClientForMedia = OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(3, 120, TimeUnit.SECONDS))
                .retryOnConnectionFailure(true)
                .addInterceptor(HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE })
                .build()

        private val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(3, 60, TimeUnit.SECONDS))
                .retryOnConnectionFailure(true)
                .addInterceptor(HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE })
                .build()

        var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var retrofitForMedia = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClientForMedia)
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }


    @POST("/franchise/logistics/getVehicleForCoordinatore")
    fun getVehicleForCoordinator(@Body data: JsonObject): Single<AssignRunnerResponse>

    @GET("franchise/logistics/findRunners")
    fun getRunnerList(): Single<FranchRunnersList>



    @POST("franchise/logistics/assignRunner")
    fun assignRunner(@Body data: JsonObject): Single<SendAssignRunner>



    @POST("franchise/logistics/getVehicleStatus")
    fun getVehicleStatus(@Body data: JsonObject): Single<VehicleStatusResponseFran>


    @POST("franchise/logistics/getVehicleStatusForShowRoom")
    fun getVehicleStatusForShowroom(@Body data: JsonObject): Single<VehicleStatusResponseFran>

    @POST("franchise/logistics/coordinatorDashBoard")
    fun getLeadCountForDashboard(@Body data:JsonObject):Single<DashboardleadCount>


    /**
     * Franchise Logistics
     * */
    @POST("franchise/logistics/pickUpRequest")//pickup request list
    fun getFranchisePickupRequest(@Body data: JsonObject): Single<RunnerPendingResponse>

    @POST("/franchise/logistics/scheduleOrRejectUpdate")
    fun updateScheduleFranchisePickup(@Body data: JsonObject): Single<UpdateRunnerResponseBean>

    ///franchise/logistics/getVehicleStatusForRunner
    @POST("franchise/logistics/getVehicleStatusForRunner")//
    fun getFranchiseVehicleStatus(@Body data: JsonObject): Single<VehicleStatusResponse>

    @GET("franchise/logistics/getDocsForRunner/{oderId}")
    fun getDocumentsDetailsList(@Path("oderId") oderId: Int):Single<FranchiseVehicleDocList>

    @POST("franchise/logistics/bikeDocsUpload")
    fun getUploadDocumentsByRunner(@Body data: UpdateRequestBeans):Single<DocumentsResponseList>

    @POST("logistics/markDelivered")
    fun markFranchiseDelivered(@Body data: JsonObject): Single<UpdateRunnerResponseBean>

    /*@GET("logistics/rejectBike/{lead_id}")
    fun rejectBike(@Path("lead_id") lead_id: String): Single<UpdateRunnerResponseBean>*/


}

