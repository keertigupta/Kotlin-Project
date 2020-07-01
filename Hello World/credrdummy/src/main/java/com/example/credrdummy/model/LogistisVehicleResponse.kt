package com.example.credrdummy.model

data class LogistisVehicleResponse(
    val allCount: Int,
    val assigned: Int,
    val count: Int,
    val delivered: Int,
    val dispute: Int,
    val intrasist: Int,
    val isValid: Boolean,
    val message: String,
    val pending: Int,
    val rejected: Int,
    val storeId: Int,
    val vehicleStatus: List<VehicleStatu>,
    val verificationApplicable: Boolean
)