package com.darkstudio.webApp;

public class AppInfo {
    String developer, version, build, edition, licence, size;

    public AppInfo(String developer, String version, String build, String edition, String licence, String size) {
        this.developer = developer;
        this.version = version;
        this.build = build;
        this.edition = edition;
        this.licence = licence;
        this.size = size;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getVersion() {
        return version;
    }

    public String getBuild() {
        return build;
    }

    public String getEdition() {
        return edition;
    }

    public String getLicence() {
        return licence;
    }

    public String getSize() {
        return size;
    }
}
