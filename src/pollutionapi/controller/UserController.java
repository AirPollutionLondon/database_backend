//package com.example.pollutionapi.controller;
//
//import com.example.pollutionapi.model.users.IUser;
//import com.example.pollutionapi.model.users.SpringUser;
//import pollutionapi.service.UserService;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//public class UserController {
//
//  private final UserService userService = new UserService();
//
//
//  @GetMapping("/{username}")
//  public void getUserByUsername(@PathVariable(value = "username") String username) {
//  }
//
//  @PostMapping("/register")
//  public ResponseEntity<String> registerUser(@RequestBody SpringUser user) {
//    IUser dbUserModel = user.createUser();
//    try {
//      userService.addNewUser(dbUserModel);
//    } catch (IllegalArgumentException i) {
//      return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
//    }
//    return new ResponseEntity<>("Registration complete", HttpStatus.CREATED);
//  }
//
//  @PostMapping("/login")
//  public ResponseEntity<String> loginUser(@RequestHeader("Authorization") String authorizationHeader) {
//    String[] basicLogin = authorizationHeader.split(" ");
//    String encryptedLogin = basicLogin[1];
//    byte[] decodedLogin = Base64.getDecoder().decode(encryptedLogin);
//    String decodedLoginStr = new String(decodedLogin, StandardCharsets.UTF_8);
//    String[] emailPassword = decodedLoginStr.split(":");
//    try {
//      userService.validateCredentials(emailPassword[0], emailPassword[1]);
//    } catch (IllegalArgumentException i) {
//      return new ResponseEntity<>("Invalid login credentials", HttpStatus.BAD_REQUEST);
//    }
//    return new ResponseEntity<>("User logged in", HttpStatus.OK);
//  }
//}