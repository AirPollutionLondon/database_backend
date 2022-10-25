//package com.example.pollutionapi.controller;
//
//import com.example.pollutionapi.model.sensors.ISensorReading;
//import com.example.pollutionapi.model.sensors.SpringSensorReading;
//import com.example.pollutionapi.service.SensorService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/sensorreading")
//@CrossOrigin
//@RequiredArgsConstructor
//public class SensorReadingController {
//
//  private final SensorService sensorService = new SensorService();
//
//  @GetMapping("/{sensorId}")
//  public void getSensorReading() {
//
//  }
//
//  @PostMapping("/upload")
//  @ResponseStatus(HttpStatus.CREATED)
//  public void uploadSensorReading(@RequestBody SpringSensorReading sensorReading) {
//    ISensorReading dbSensorReadingModel = sensorReading.createSensorReading();
//    sensorService.addSensorReading(dbSensorReadingModel);
//  }
//}