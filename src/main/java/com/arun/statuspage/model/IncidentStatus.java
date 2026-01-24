// Location: src/main/java/com/arun/statuspage/model/IncidentStatus.java
package com.arun.statuspage.model;

public enum IncidentStatus {
    INVESTIGATING("Investigating"),
    IDENTIFIED("Identified"),
    MONITORING("Monitoring"),
    RESOLVED("Resolved");

    private final String displayName;

    IncidentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}