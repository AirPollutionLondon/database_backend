package pollutionapi.model.queries;

import pollutionapi.model.sensors.SensorReading;
import java.security.Timestamp;
import java.util.Date;


/**
 * Represents the time range query for sensor readings.
 *
 * @author Antoine Assaf, Jenny Do
 */
public class TimeRangeQuery implements IQuery<SensorReading> {
    private final Timestamp timeLowerBound;
    private final Timestamp timeUpperBound;

    /**
     * Instantiates the time range filter that includes all sensor readings within the time
     * range set by the two time lower bound.
     *
     * @param timeLowerBound time lower bound
     * @param timeUpperBound time upper bound
     */
    public TimeRangeQuery(Timestamp timeLowerBound, Timestamp timeUpperBound) {
        this.timeLowerBound = timeLowerBound;
        this.timeUpperBound = timeUpperBound;
    }

    /**
     * Filters a new sensor reading collection that fall within the given time range.
     *
     * @param sensorReadings The collection of readings to filter.
     * @return The filtered collection of sensor readings.
     */
    public boolean filter(SensorReading sr) {
        Timestamp ts = (Timestamp) sr.getField("timeStamp");
        Date date = ts.getTimestamp();

        return (date.after(this.timeLowerBound.getTimestamp())
                && date.before(this.timeUpperBound.getTimestamp()));
    }
}
