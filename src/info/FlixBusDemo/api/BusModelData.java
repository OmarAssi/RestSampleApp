package info.FlixBusDemo.api;

import android.os.Build;

import java.util.Objects;

/**
 * Created by omar.assi on 4/24/2016.
 */
public class BusModelData {
    String stations;
    String dateTime;
    String lineDirection;
    String briefRoute;
    String coordinates;
    String lineCode;
    String direction;
    String delayStatus;
    String delayTime;

    public BusModelData() {
    }

    public String getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(String delayTime) {
        this.delayTime = delayTime;
    }

    public String getDelayStatus() {
        return delayStatus;
    }

    public void setDelayStatus(String delayStatus) {
        this.delayStatus = delayStatus;
    }

    public static int get(int position) {
        return position;
    }

    public String getStations() {
        return stations;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLineDirection() {
        return lineDirection;
    }

    public void setLineDirection(String lineDirection) {
        this.lineDirection = lineDirection;
    }

    public String getBriefRoute() {
        return briefRoute;
    }

    public void setBriefRoute(String briefRoute) {
        this.briefRoute = briefRoute;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusModelData)) return false;
        BusModelData that = (BusModelData) o;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.equals(getStations(), that.getStations()) &&
                    Objects.equals(getDateTime(), that.getDateTime()) &&
                    Objects.equals(getLineDirection(), that.getLineDirection()) &&
                    Objects.equals(getBriefRoute(), that.getBriefRoute()) &&
                    Objects.equals(getCoordinates(), that.getCoordinates()) &&
                    Objects.equals(getLineCode(), that.getLineCode()) &&
                    Objects.equals(getDirection(), that.getDirection()) &&
                    Objects.equals(getDelayStatus(), that.getDelayStatus()) &&
                    Objects.equals(getDelayTime(), that.getDelayTime());
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStations(), getDateTime(), getLineDirection(), getBriefRoute(), getCoordinates(), getLineCode(), getDirection(), getDelayStatus(), getDelayTime());
    }

}
