package com.vikjo.openmicevent1;

public class Artist {

    public String artistname,stagename,skills,artistphoneno,artistage,artistemail, userType;

    public Artist(){

    }

    public Artist(String artistname, String stagename, String skills, String artistphoneno, String artistage, String artistemail, String userType) {
        this.artistname = artistname;
        this.stagename = stagename;
        this.skills = skills;
        this.artistphoneno = artistphoneno;
        this.artistage = artistage;
        this.artistemail = artistemail;
        this.userType = userType;

    }



    public String getArtistname() {
        return artistname;
    }

    public String getStagename() {
        return stagename;
    }

    public String getSkills() {
        return skills;
    }

    public String getArtistphoneno() {
        return artistphoneno;
    }

    public String getArtistage() {
        return artistage;
    }

    public String getArtistemail() {
        return artistemail;
    }


}
