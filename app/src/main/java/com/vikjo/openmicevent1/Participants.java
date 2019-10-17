package com.vikjo.openmicevent1;

public class Participants {

    public String eventnameforparticipants, participantname,participantstagename, participantage, participantskills;

    public Participants(String eventnameforparticipants, String participantname, String participantstagename, String participantage, String participantskills) {
        this.eventnameforparticipants = eventnameforparticipants;
        this.participantname = participantname;
        this.participantstagename = participantstagename;
        this.participantage = participantage;
        this.participantskills = participantskills;
    }

    public String getEventnameforparticipants() {
        return eventnameforparticipants;
    }

    public void setEventnameforparticipants(String eventnameforparticipants) {
        this.eventnameforparticipants = eventnameforparticipants;
    }

    public String getParticipantname() {
        return participantname;
    }

    public void setParticipantname(String participantname) {
        this.participantname = participantname;
    }

    public String getParticipantstagename() {
        return participantstagename;
    }

    public void setParticipantstagename(String participantstagename) {
        this.participantstagename = participantstagename;
    }

    public String getParticipantage() {
        return participantage;
    }

    public void setParticipantage(String participantage) {
        this.participantage = participantage;
    }

    public String getParticipantskills() {
        return participantskills;
    }

    public void setParticipantskills(String participantskills) {
        this.participantskills = participantskills;
    }

    public Participants(){


    }

}
