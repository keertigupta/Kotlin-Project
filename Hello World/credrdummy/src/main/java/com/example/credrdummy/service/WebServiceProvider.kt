package connect.com.credr.connect.services

import com.google.gson.JsonObject
import connect.com.credr.connect.BuildConfig
import connect.com.credr.connect.models.*
import connect.com.credr.connect.models.verification.VerificationResponse
import connect.com.credr.connect.ui.bidding.model.AuctionDetailsBean
import connect.com.credr.connect.ui.bidding.model.LeadBean
import connect.com.credr.connect.ui.customer.model.CreateOrderRequest
import connect.com.credr.connect.ui.customer.model.OrderResponseBean
import connect.com.credr.connect.ui.customer.model.PenaltyResponseBean
import connect.com.credr.connect.ui.dashboard.lead_count_pojo.LeadCountPojo
import connect.com.credr.connect.ui.gm.model.LeadResponseBean
import connect.com.credr.connect.ui.login.models.LoginResponse
import connect.com.credr.connect.ui.login.models.UserTypeListBean
import connect.com.credr.connect.ui.logistics.assign.model.CoordinatorResponseBean
import connect.com.credr.connect.ui.logistics.assign.model.RunnerListResponseBean
import connect.com.credr.connect.ui.logistics.assign.model.UpdateRunnerResponseBean
import connect.com.credr.connect.ui.logistics.documents_update.UpdateRequestBeans
import connect.com.credr.connect.ui.logistics.documents_update.runner_cordinator_docUpload.RunnerDocListResponse
import connect.com.credr.connect.ui.logistics.runner.model.ConfirmLeadRequest
import connect.com.credr.connect.ui.logistics.runner.model.RunnerLeadResponseBean

import connect.com.credr.connect.ui.logistics.vehiclestatus.model.VehicleStatusResponseBean
import connect.com.credr.connect.ui.notification.model.NotificationsResponseBean
import connect.com.credr.connect.ui.seller_details.seller_pojo.SellerCityResponseBeans
import connect.com.credr.connect.ui.seller_details.seller_pojo.SellerResponseBeanList
import connect.com.credr.connect.ui.seller_details.seller_pojo.SellerStatusBeans
import connect.com.credr.connect.ui.shd.bidding_status.models.BiddingStatus
import connect.com.credr.connect.ui.valuator.complete_exchange.complted_pojo.ExchangeResponseList
import connect.com.credr.connect.ui.valuator.complete_exchange.complted_pojo.VoucherResponse
import connect.com.credr.connect.ui.valuator.complete_exchange.sub_activity.DocumentsResponseList
import connect.com.credr.connect.ui.valuator.complete_exchange.sub_activity.documents_pojo.AddImageRequest
import connect.com.credr.connect.ui.valuator.lead_summary.model.ValuatorResponseBean
import connect.com.credr.connect.ui.valuator.vehicle_status.model.ValuatorVehicleListResponseBean
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
interface WebServiceProvider {

    companion object {

        private const val LOCAL_BASE_URL="http://192.168.2.76:8080/"//

        private const val PRE_PROD_BASE_URL="http://52.74.127.90:8080/"
        private const val PROD_BASE_URL="http://apiv2.credr.com:8080/"
        private const val BASE_URL =  BuildConfig.BASE_URL

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

    @GET("lead/bike/makers")
    fun getMake(): Single<BikeModel>

    @GET("lead/bike/models/{makeId}")
    fun getModels(@Path("makeId") makeId: String): Single<BikeModel>

    @GET("lead/bike/variants/{modelId}")
    fun getVariants(@Path("modelId") modelId: String): Single<BikeModel>


    @POST("user/login")
    fun login(@Body data: JsonObject): Single<LoginResponse>

    @POST("lead/saveInspectedParams")
    fun saveLeadInspection(@Body data: SaveInspectionRequest): Single<LeadBean>

    @POST("lead/add_lead")
    fun saveVehicleDetail(@Body data: SaveVehicleRequest): Single<ResponseBody>


    @Multipart
    @POST("user/uploadFile")
    fun uploadImage(@Part file: MultipartBody.Part
                    , @Part("fileName") fileName: RequestBody
                    , @Part("folderName") folderName: RequestBody): Single<ResponseBody>


    @POST("auction/create_auction")
    fun createAuction(@Body data: JsonObject): Single<AuctionDetailsBean>

    @GET("auction/getAuctionDetails/{auctionId}")
    fun refreshAuctionDetails(@Path("auctionId") auctionId: String): Single<AuctionDetailsBean>

    @GET("auction/getAuctionDetailsByUser/{auctionId}/{userId}")
    fun refreshAuctionDetails(@Path("auctionId") auctionId: String,@Path("userId") userId: String): Single<AuctionDetailsBean>



    @POST("auction/rebid")
    fun reBid(@Body data: JsonObject): Single<AuctionDetailsBean>


    @POST("auction/storeRebid")
    fun storeReBid(@Body data: JsonObject): Single<AuctionDetailsBean>

    @POST("auction/getSHDBiddingStatusList")
    fun getSHDBiddingStatusList(@Body data: JsonObject): Single<BiddingStatus>


    @POST("auction/bid")
    fun auctionBid(@Body data: JsonObject): Single<AuctionDetailsBean>

    @POST("user/fcm-token")
    fun registerDeviceToken(@Body obj: JsonObject): Single<ResponseBody>

    @POST("user/logout")
    fun logout(@Body obj: JsonObject): Single<LogoutResponseBean>

  @PUT("order/updateOrderDetails")
    fun updateOrderDetails(@Body obj: UpdateOrderRequest): Single<ResponseBean>

    @GET("auction/vehicleDocumentValue/{cityId}")
    fun getPenaltyDetails(@Path("cityId") cityId: String): Single<PenaltyResponseBean>

    @GET("order/getOrderDetails/{orderId}")
    fun getOrderDetails(@Path("orderId") orderId: String): Single<ResponseBean>

    @GET("lead/inspectionparam/{leadId}")
    fun getInspectedLead(@Path("leadId") leadId: String): Single<InspectedLeadDao>


    @POST("order/createOrder")
    fun createTransction(@Body data: CreateOrderRequest): Single<OrderResponseBean>


    @GET("lead/paramtype/{leadId}")
    fun getLeadInspectionPArams(@Path("leadId") userId: String): Single<InspectionParameterDao>

    @GET("user/notifications_list/{userId}")
    fun getNotificationsList(@Path("userId") userId: String): Single<NotificationsResponseBean>


    @POST("lead/addLeadFollowUp")
    fun updateFallowUp(@Body data: JsonObject): Single<NotificationsResponseBean>


    @POST("auction/getValuatorBiddingStatusList")
    fun getValuatorLeadStatusList(@Body data: JsonObject): Single<ValuatorResponseBean>

    @POST("auction/rejectByValuatorRecreateAuction")
    fun rejectAuction(@Body data: JsonObject): Single<AuctionDetailsBean>

    @POST("payment/addBankDetails")
    fun addBankDetails(@Body data: JsonObject): Single<ResponseBody>

    @GET("auction/getAuctionDetailsByGatepass/{gatePassId}")
    fun refreshAuctionDetailsByGatePassId(@Path("gatePassId") gatePassId: String): Single<AuctionDetailsBean>

    @GET("lead/vehicledetails/{leadId}")
    fun getVehicleDetailByLeadId(@Path("leadId") leadId: Int): Single<LeadVehicleDetailDao>

    @GET("lead/fhdOutlets/{userId}")
    fun getFhdOutlets(@Path("userId") userId: String): Single<StoreResponseBean>

    @GET("auction/getLeadsByFhdId/{userId}/{status}")
    fun getFHDLeadStatusList(@Path("userId") userId: String, @Path("status") status: String): Single<ValuatorResponseBean>


    @POST("user/forgotpassword")
    fun forgotPassword(@Body data: JsonObject): Single<ForgotResponseBean>

    @GET("user/app-version")
    fun fetchAppVersion(): Single<AppInfoResponse>

    @GET("user/new-app-version/{userId}")
    fun fetchAppNewVersion(@Path("userId") userId: Int): Single<AppInfoResponse>

    @POST("auction/endStoreAuction")
    fun endStoreAuction(@Body data: JsonObject): Single<AuctionDetailsBean>


    @POST("auction/rejectBBOffer")
    fun rejectBBOffer(@Body data: JsonObject): Single<AuctionDetailsBean>


    @POST("auction/acceptBBOffer")
    fun acceptBBOffer(@Body data: JsonObject): Single<AuctionDetailsBean>



    @POST("user/tracking")
    fun userTracking(@Body data: JsonObject): Single<ResponseBody>



    @POST("lead/leadSummary")
    fun getLeadSummary(@Body data: JsonObject): Single<LeadResponseBean>


    @POST("logistics/getVehicleForCoordinatore")
    fun getVehicleForCoordinator(@Body data: JsonObject): Single<CoordinatorResponseBean>

    @GET("logistics/getRunner/{cityId}")
    fun getRunnerList(@Path("cityId") cityId: String): Single<RunnerListResponseBean>



    @POST("logistics/assignRunner")
    fun assignRunner(@Body data: JsonObject): Single<UpdateRunnerResponseBean>



    @POST("logistics/getVehicleStatus")
    fun getVehicleStatus(@Body data: JsonObject): Single<VehicleStatusResponseBean>


    @POST("logistics/getVehicleForRunner")
    fun getVehicleForRunner(@Body data: JsonObject): Single<RunnerLeadResponseBean>


    @GET("logistics/rejectBike/{lead_id}")
    fun rejectBike(@Path("lead_id") lead_id: String): Single<UpdateRunnerResponseBean>


    @POST("logistics/schedulePickup")
    fun updateSchedulePickup(@Body data: JsonObject): Single<UpdateRunnerResponseBean>


    @POST("logistics/confirmPickup")
    fun confirmPickup(@Body data: ConfirmLeadRequest): Single<UpdateRunnerResponseBean>


    @POST("logistics/verifyOtp")
    fun verifyOtp(@Body data: JsonObject): Single<UpdateRunnerResponseBean>



    @POST("logistics/markDelivered")
    fun markDelivered(@Body data: JsonObject): Single<UpdateRunnerResponseBean>



    @POST("logistics/getVehicleForValuator")
    fun getValuatorVehicleList(@Body data: JsonObject): Single<ValuatorVehicleListResponseBean>




    @POST("logistics/warehouseStatus")
    fun getWarehouseVehicleList(@Body data: JsonObject): Single<VehicleStatusResponseBean>


    @POST("logistics/confirmDeliveredCoordinatoreWeb")
    fun confirmDeliveredCoordinatore(@Body data: ConfirmLeadRequest): Single<UpdateRunnerResponseBean>


    @POST("lead/cppPrice")
    fun getCPPPrice(@Body data: JsonObject): Single<UpdateRunnerResponseBean>


    @GET("user/getusertype")
    fun getUserTypeList(): Single<UserTypeListBean>


    @GET("lead/mmvSearch/{mmv}")
    fun getBikeList(@Path("mmv") mmv: String): Single<MMVResponseBean>



    @POST("lead/generateOtp")
    fun generateOTP(@Body data: JsonObject): Single<OtpResponseBean>


    @POST("lead/verifyOTP")
    fun verifyOTPByValuator(@Body data: JsonObject): Single<OtpResponseBean>


    @POST("lead/addNewlead")
    fun addLead(@Body data: SaveVehicleRequest): Single<ResponseBody>


    @POST("lead/generateOtp")
    fun resend(@Body data: JsonObject): Single<OtpResponseBean>


    @POST("lead/ownerDetails")
    fun uploadIdDocuments(@Body data: JsonObject): Single<VerificationResponse>


    @POST("lead/rcDetails")
    fun uploadRCDocuments(@Body data: JsonObject): Single<VerificationResponse>


    @POST("lead/verifyByValuator")
    fun verificationStatusUpdate(@Body data: JsonObject): Single<ResponseBean>


    @GET("lead/getBikeDocList")
    fun getBikeDocumentList(): Single<BikeDocumentResponseBean>



    @POST("lead/aadharOtpVerify")
    fun getAadharDetails(@Body data: JsonObject): Single<VerificationResponse>


    @POST("lead/bikeDocumentsUpload")
    fun uploadDouments(@Body data:BikeDocumentUploadRequest): Single<ResponseBean>


    @POST("lead/challan")
    fun uploadChallanDocuments(@Body data: ChallanRequest): Single<VerificationResponse>

    @POST("lead/statusUpdate")
    fun updateLeadStatus(@Body data: JsonObject): Single<NotificationsResponseBean>


    /**
     * SellerUpdate
    * */

   @POST("crm/leads/sellonly")
   fun getSellerDeatils(@Body data:JsonObject):Single<SellerResponseBeanList>

    @POST("crm/voicecall")
    fun makeCallToSeller(@Body data:JsonObject):Single<SellerResponseBeanList>

    @GET("crm/getcities")
    fun getCitiesList():Single<SellerCityResponseBeans>

    @GET("crm/getallstatuslist/sell_only")
    fun getSellerStatusList():Single<SellerStatusBeans>

    @POST("crm/update/C2B")
    fun updateSellerStatus(@Body data:JsonObject):Single<SellerStatusBeans>


    /**
     *        Completed Exchanges
     *
     * */

    @POST("lead/completedExchanges")
    fun getExchangesList(@Body data:JsonObject):Single<ExchangeResponseList>

    @POST("lead/getDashBoard")
    fun getLeadCountForValuator(@Body data:JsonObject):Single<LeadCountPojo>


    @POST("lead/getQcBikeDocsByValuator")// http://localhost:8080/lead/getQcBikeDocsByValuator/12671,lead/getDisputedQcBikeDocs/{leadId}
    fun getBikeDocumentsDetails(@Body data:JsonObject): Single<DocumentsResponseList>


    @GET("lead/getDisputedQcBikeDocs/{leadId}")// http://localhost:8080/lead/getQcBikeDocsByValuator/12671,lead/getDisputedQcBikeDocs/{leadId}
    fun getRejectedBikeDocuments(@Path("leadId") leadId: String): Single<DocumentsResponseList>

    @GET("getQcBikeDocs/{leadId}")
    fun getQcDocumentsDetails(@Path("leadId") leadId: String): Single<DocumentsResponseList>


    @POST("lead/docsUpdationByValuator")
    fun updateDocumentsImage(@Body data: AddImageRequest):Single<DocumentsResponseList>

    /**
     * Runner Details
     * */
    @GET("logistics/getDocsForRunner/{leadId}")
    fun getDocumentsList(@Path("leadId") leadId: Int):Single<RunnerDocListResponse>

    @POST("logistics/runnerDashBoard")
    fun getLeadCountForLogistics(@Body data:JsonObject):Single<LeadCountPojo>

    @POST("logistics/DocumentUploadByRunner")
    fun getDocumentsUpload(@Body data: UpdateRequestBeans):Single<DocumentsResponseList>

    @GET("lead/exchangeDetails/{leadId}")
    fun getVoucherDetails(@Path("leadId") leadId: Int):Single<VoucherResponse>
}

