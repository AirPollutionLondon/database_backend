package pollutionapi.model.sensors;

public class SpringSensor {
  private String email;
  private String serialNumber;
  private String status;
  private float latitude;
  private float longitude;

  public SensorInfo createSensorInfo() {
    return new SensorInfo(email, serialNumber, status, latitude, longitude);
  }
}