// Location: src/main/java/com/arun/statuspage/model/IncidentSeverity.java
package com.arun.statuspage.model;

public enum IncidentSeverity {
    MINOR("Minor", "info"),
    MAJOR("Major", "warning"),
    CRITICAL("Critical", "danger");

    private final String displayName;
    private final String cssClass;

    IncidentSeverity(String displayName, String cssClass) {
        this.displayName = displayName;
        this.cssClass = cssClass;
    }

    public String getDisplayName() { return displayName; }
    public String getCssClass() { return cssClass; }
}