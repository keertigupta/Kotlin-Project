
package connect.com.credr.connect.ui.logistics.runner.vehiclestatus.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import connect.com.credr.connect.ui.logistics.vehiclestatus.model.VehicleStatus;

public class VehicleStatusResponseBean implements Serializable
{

    @SerializedName("isValid")
    @Expose
    private boolean isValid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("vehicleStatus")
    @Expose
    private List<connect.com.credr.connect.ui.logistics.vehiclestatus.model.VehicleStatus> vehicleStatus = null;
    @SerializedName("count")
    @Expose
    private int count;
    private final static long serialVersionUID = 884804894708692686L;

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<connect.com.credr.connect.ui.logistics.vehiclestatus.model.VehicleStatus> getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(List<VehicleStatus> vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
