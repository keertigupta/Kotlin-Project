package com.example.credrdummy

/**
 * Created by Shivam Jaiswal on 22/03/18.
 */
class Constants {


    companion object {
        const val BIDDING_ONGOING = "ONGOING"
        const val BIDDING_WON = "WON"
        const val BIDDING_LOST = "LOST"
        const val BIDDING_MISSED = "MISSED"
        const val BIDDING_ALL = "ALL"
        const val REJECTED = "REJECTED"

        const val ONGOING = "ONGOING"
        const val AUCTION_STARTED = "AUCTION_STARTED"
        const val EXCHANGE = "EXCHANGE"
        const val DRAFT = "DRAFT"
        const val DRAFT_AUCTION_READY = "DRAFT_AUCTION_READY"
        const val SELL = "SELL"
        const val ALL = "ALL"
        const val DROPPED = "DROPPED"
        const val FOLLOWUP = "FOLLOWUP"

        const val PRE_APPROVED = "PRE_APPROVED"

        val SERVICE_LEAD = "service_leads"
        const val ADD_LEAD = "vltr_add_lead"
        val LEAD_SUMMARY = "vltr_lead_summary"
        val BIDDING_STATUS = "shd_bid_status"
        val SON_BIKE_STATUS = "shd_won_status"

        val ASSIGN_RUNNER_FRANCHISE = "franchise_logistics_assign_runner"
        val VEHICLE_STATUS_FRANCHISE = "franchise_logistics_vehicle_status"
        val SHOW_ROOM_DELIVERY = "franchise_logistics_showroom_delivery"
        val RUNNER_PICKUP_FRANCHISE = "franchise_logistics_runner_pickup_request"
        val RUNNER_STATUS_FRANCHISE  ="franchise_logistics_runner_vehicle_status"
        val RUNNER_SHOW_ROOM_DELIVERY ="franchise_logistics_runner_showrom_delivery"
        val PICK_UP_DISPUTE = "PICKUP_DISPUTE"




        val FHD_LEAD_SUMMARY = "fhd_lead_summary"
        val GM_LEAD_SUMMARY = "gm_lead_summary"

        val ASSIGN_RUNNER = "assign_runner"
        val VEHICLE_STATUS = "vehicle_status"
        val PICKUP_REQUEST="pickup_request"
        val RUNNER_VEHICLE_STATUS="runner_vehicle_status"
        val VALUATOR_VEHICLE_STATUS="valuator_vehicle_status"

        val WARE_HOUSE_STATUS="ware_house_status"

        val PENDING_CONFIRMATION="PENDING CONFIRMATION"
        val DELIVERED_AT_WAREHOUSE="DELIVERED AT WAREHOUSE"

        val DELIVERED_CONFIRM_PENDING="DELIVERED_CONFIRM_PENDING"


        const val PENDING_FRAN = "PENDING_PICKUP"
        const val INTRANSI_FRAN  = "IN_TRANSIT"
        const val DELIVERED_FRAN = "DELIVERED"
        const val DISPUTE_FRAN = "DISPUTE"
        const val DELIVERY_DISPUTE ="DELIVERY_DISPUTE"
        const val ASSIGNED_FRAN = "ASSIGNED"
        const val PENDING_CONFIRMATION_FRAN = "PENDING_CONFIRMATION"

        const val PENDING = "PENDING"
        const val INTRANSIT = "INTRANSIT"
        const val DELIVERED = "DELIVERED"
        const val DISPUTE = "DISPUTE"
        const val ASSIGNED = "ASSIGNED"
        const val PENDING_CONFIRM= "PENDING_CONFIRMATION"
        const val PENDING_DELIVERY_CONFIRM= "DELIVERED_CONFIRM_PENDING"

        const val FRAGMENT_DASHBOARD = 1
        const val FRAGMENT_PROFILE = 2
        const val FRAGMENT_NOTIFICATIONS = 3


        const val VALUATOR_ADD_LEAD = 15
        const val VALUATOR_LEAD_SUMMARY = 16
        const val SHD_BIDDING_STATUS = 17
        const val SHD_WONBIKE_STATUS = 18
        const val SHD_INVENTORY_ACTIONS = 19

        const val FHD_LEAD_SUMMARY_ID = 30


        const val USERTYPE_VALUATOR = "VALUATOR"
        const val USERTYPE_SHD = "SHD"
        const val USERTYPE_STORE = "STORE"
        const val BUYBACK = "BUYBACK"


        val NO_OF_AFTER_TEST_RIDE = "Kms Driven after Test Drive"
        val NO_OF_BEFORE_TEST_RIDE = "Kms Driven Before Test Drive"
        val PRICE_TXT = "Rs. {0}"
        val PENALTY_PRICE_TXT = "{0} (-)"
        val DEALER_TXT = "Dealer {0}"

        val AADHAR="AADHAR"
        val PAN="PAN"
        val VOTERID="VOTERID"
        val LICENSE="LICENSE"
        val RC="RC"

        const val SELL_ONLY_LEAD="sell_only_leads"
        val SELLER_NEW="new"
        val SELLER_FOLLOW_UP="follow up"
        val SELLER_DROP="drop"
        val SELLER_CONDUCT="conduct"
        val SELLER_CONFIRM="confirm"
        val SELLER_EXCHANGE="exchange"


        const val COMPLED_EXCHANGE="vltr_completed_exchanges"
        val EXCHANGES_ALL="ALL"
        val QC_PENDING="PENDING"
        val EX_APPROVED="APPROVED"
        val EX_DISPUTE="DISPUTE"
        val EX_REJECTED="REJECTED"

        const val APPROVE="APPROVE"


        const val REQUEST_CODE_DOCUMETS=102

        const val FRANCHISE_COOR_DASHBOARD="FRANCHISE_LOGISTICS_COORDINATOR"
        const val VALUATOR_DASHBOARD="Valuator"
        const val RUNNER_DASHBOARD="LOGISTICS_RUNNER"
        const val COORDINATOR_DASHBOARD="LOGISTICS_COORDINATOR"
        const val LOGISTICS_COORDINATOR_CENTRAL="LOGISTICS_COORDINATOR_CENTRAL"
        const val FRANCHISE_LOGISTICS_COORDINATOR="FRANCHISE_LOGISTICS_COORDINATOR"

        const val MECHANICAL="MECHANICAL"
        const val ELECTRICALS="ELECTRICALS"
        const val AESTHETICS="AESTHETICS"
        const val ADD_PHOTOS="ADD PHOTOS"
        const val ADD_VIDEO="ADD VIDEO"
        const val PENDING_PUCKUP="PENDING_PICKUP"

        const val FRANCHISE_PICKUP="franchise_logistics_runner_pickup_request"
        const val FRANCHISE_VEHICLE_STATUS="franchise_logistics_runner_vehicle_status"

          const val PENDING_CONFIRMATION_FRANCHISE="PENDING_CONFIRMATION"
         const val IN_TRANSIT="IN_TRANSIT"
        const val PICKUP_DISPUTE="PICKUP_DISPUTE"
        const val PENDING_PICKUP="PENDING_PICKUP"
        const val DELIVERED_FRANCHISE="DELIVERED"
    }
}