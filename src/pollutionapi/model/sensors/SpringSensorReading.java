package pollutionapi.model.sensors;

public class SpringSensorReading {
  private String serialNumber;
  private String timeStamp;
  private Integer voc;
  private Integer co2;
  private Integer spm1_0;
  private Integer spm2_5;
  private Integer spm10;
  private Integer aec1_0;
  private Integer aec2_5;
  private Integer aec10;

  public ISensorReading createSensorReading() {
    return new SensorReading(serialNumber, timeStamp, voc, co2, spm1_0, spm2_5, spm10, aec1_0, aec2_5, aec10);
  }
}