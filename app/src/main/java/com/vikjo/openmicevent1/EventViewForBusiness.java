package com.vikjo.openmicevent1;

public class EventViewForBusiness {

    private String eventname,businessname,eventdate,eventtime,eventlocation;


    public EventViewForBusiness(String eventname, String businessname, String eventdate, String eventtime, String eventlocation) {
        this.eventname = eventname;
        this.businessname = businessname;
        this.eventdate = eventdate;
        this.eventtime = eventtime;
        this.eventlocation = eventlocation;
    }


    public EventViewForBusiness(){

    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public void setEventlocation(String eventlocation) {
        this.eventlocation = eventlocation;
    }
}
