
package connect.com.credr.connect.ui.logistics.assign.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoordinatorLead implements Serializable
{

    @SerializedName("leadId")
    @Expose
    private String leadId;
    @SerializedName("leadCreatedDate")
    @Expose
    private String leadCreatedDate;
    @SerializedName("makeName")
    @Expose
    private String makeName;
    @SerializedName("modelName")
    @Expose
    private String modelName;
    @SerializedName("variantName")
    @Expose
    private String variantName;
    @SerializedName("registrationNum")
    @Expose
    private String registrationNum;
    @SerializedName("storeName")
    @Expose
    private String storeName;

    @SerializedName("vehicleLeadStatus")
    @Expose
    private String vehicleLeadStatus;

    @SerializedName("orderCreatedDate")
    @Expose
    private String orderCreatedDate;





    private final static long serialVersionUID = 671204039331491397L;

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getLeadCreatedDate() {
        return leadCreatedDate;
    }

    public void setLeadCreatedDate(String leadCreatedDate) {
        this.leadCreatedDate = leadCreatedDate;
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

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getVehicleLeadStatus() {
        return vehicleLeadStatus;
    }

    public void setVehicleLeadStatus(String vehicleLeadStatus) {
        this.vehicleLeadStatus = vehicleLeadStatus;
    }

    public String getOrderCreatedDate() {
        return orderCreatedDate;
    }

    public void setOrderCreatedDate(String orderCreatedDate) {
        this.orderCreatedDate = orderCreatedDate;
    }
}
