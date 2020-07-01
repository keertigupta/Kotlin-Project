
package connect.com.credr.connect.ui.logistics.assign.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoordinatorResponseBean implements Serializable
{

    @SerializedName("isValid")
    @Expose
    private boolean isValid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("coordinatorLead")
    @Expose
    private List<CoordinatorLead> coordinatorLead = null;
    private final static long serialVersionUID = 95368309791677889L;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CoordinatorLead> getCoordinatorLead() {
        return coordinatorLead;
    }

    public void setCoordinatorLead(List<CoordinatorLead> coordinatorLead) {
        this.coordinatorLead = coordinatorLead;
    }

}
