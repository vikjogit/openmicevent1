package com.vikjo.openmicevent1;

public class MyArtistEvent {

String eventforparticipants;

    public MyArtistEvent(String eventforparticipants) {
        this.eventforparticipants = eventforparticipants;
    }

    public void setEventforparticipants(String eventforparticipants) {
        this.eventforparticipants = eventforparticipants;
    }

    public MyArtistEvent(){


    }

    public String getEventforparticipants() {
        return eventforparticipants;
    }
}
