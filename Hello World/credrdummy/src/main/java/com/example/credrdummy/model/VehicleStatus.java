
package connect.com.credr.connect.ui.logistics.runner.vehiclestatus.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleStatus implements Serializable
{

    @SerializedName("leadId")
    @Expose
    private String leadId;
    @SerializedName("vehicleStatus")
    @Expose
    private String vehicleStatus;
    @SerializedName("makeName")
    @Expose
    private String makeName;
    @SerializedName("modelName")
    @Expose
    private String modelName;
    @SerializedName("variantName")
    @Expose
    private String variantName;
    @SerializedName("makeYear")
    @Expose
    private String makeYear;
    @SerializedName("registrationNumber")
    @Expose
    private String registrationNumber;
    @SerializedName("fhdName")
    @Expose
    private String fhdName;
    @SerializedName("pickUpDate")
    @Expose
    private String pickUpDate;
    @SerializedName("deliveredDate")
    @Expose
    private String deliveredDate;
    @SerializedName("orderCreatedDate")
    @Expose
    private String orderCreatedDate;
    @SerializedName("coordinatorId")
    @Expose
    private String coordinatorId;
    @SerializedName("runnerId")
    @Expose
    private String runnerId;
    @SerializedName("runnerName")
    @Expose
    private String runnerName;
    @SerializedName("runnerMobileNumber")
    @Expose
    private String runnerMobileNumber;
    private final static long serialVersionUID = -8624693200225179993L;

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getMakeYear() {
        return makeYear;
    }

    public void setMakeYear(String makeYear) {
        this.makeYear = makeYear;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getFhdName() {
        return fhdName;
    }

    public void setFhdName(String fhdName) {
        this.fhdName = fhdName;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getOrderCreatedDate() {
        return orderCreatedDate;
    }

    public void setOrderCreatedDate(String orderCreatedDate) {
        this.orderCreatedDate = orderCreatedDate;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(String runnerId) {
        this.runnerId = runnerId;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }

    public String getRunnerMobileNumber() {
        return runnerMobileNumber;
    }

    public void setRunnerMobileNumber(String runnerMobileNumber) {
        this.runnerMobileNumber = runnerMobileNumber;
    }

}
