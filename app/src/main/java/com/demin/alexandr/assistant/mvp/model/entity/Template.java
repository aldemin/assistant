package com.demin.alexandr.assistant.mvp.model.entity;

import com.google.firebase.Timestamp;

import java.util.Objects;

public class Template {

    public static final String TEMPLATE_DAYS_TO_FREEZE_FIELD_KEY = "days_to_freeze";
    public static final String TEMPLATE_EXPIRY_DATE_FIELD_KEY = "expiry_date";
    public static final String TEMPLATE_QUANTITY_OF_VISITS_FIELD_KEY = "quantity_of_visits";
    public static final String TEMPLATE_QUANTITY_TO_SKIP_FIELD_KEY = "quantity_to_skip";
    public static final String TEMPLATE_START_DATE_FIELD_KEY = "start_date";
    public static final String TEMPLATE_TITLE_FIELD_KEY = "title";
    public static final String TEMPLATE_UNLIMITED_FIELD_KEY = "unlimited";
    public static final String TEMPLATE_ID_KEY = "id";
    public static final String TEMPLATE_OWNER_ID_KEY = "owner_id";

    private String ownerId;
    private String id;
    private String title;
    private long daysToFreeze;
    private long quantityOfVisits;
    private long quantityToSkip;
    private Timestamp expiryDate;
    private Timestamp startDate;
    private boolean unlimited;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDaysToFreeze() {
        return daysToFreeze;
    }

    public void setDaysToFreeze(long daysToFreeze) {
        this.daysToFreeze = daysToFreeze;
    }

    public long getQuantityOfVisits() {
        return quantityOfVisits;
    }

    public void setQuantityOfVisits(long quantityOfVisits) {
        this.quantityOfVisits = quantityOfVisits;
    }

    public long getQuantityToSkip() {
        return quantityToSkip;
    }

    public void setQuantityToSkip(long quantityToSkip) {
        this.quantityToSkip = quantityToSkip;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public boolean isUnlimited() {
        return unlimited;
    }

    public void setUnlimited(boolean unlimited) {
        this.unlimited = unlimited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return Objects.equals(id, template.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
