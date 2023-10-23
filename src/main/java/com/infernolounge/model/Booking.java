package com.infernolounge.model;

import java.time.LocalDateTime;

public class Booking {
    private int id;
    //table should exist, drop-down list on crm side
    private int tableId;
    private int clientId;
    private String name;
    //crm has the validation for client card and tel number. its have to belong to one client
    private String phone;
    private int people;
    private String comment;
    private int status;
    private LocalDateTime bookedFrom;
    private LocalDateTime bookedTill;
    //int validation
    private float bookedHours;
    private int hookahsCount;
    //this field must be not null and unique for booking process
    //crm has the validation for client card and tel number. its have to belong to one client
    private int clientCard;
    private String device;

}
