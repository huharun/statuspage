// Location: src/main/java/com/arun/statuspage/model/ServiceStatus.java
package com.arun.statuspage.model;

public enum ServiceStatus {
    OPERATIONAL("All Systems Operational", "success", "#10b981"),
    DEGRADED_PERFORMANCE("Degraded Performance", "warning", "#f59e0b"),
    PARTIAL_OUTAGE("Partial Outage", "warning", "#f97316"),
    MAJOR_OUTAGE("Major Outage", "danger", "#ef4444");

    private final String displayName;
    private final String cssClass;
    private final String color;

    ServiceStatus(String displayName, String cssClass, String color) {
        this.displayName = displayName;
        this.cssClass = cssClass;
        this.color = color;
    }

    public String getDisplayName() { return displayName; }
    public String getCssClass() { return cssClass; }
    public String getColor() { return color; }
}