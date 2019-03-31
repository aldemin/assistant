package com.demin.alexandr.assistant.recycle.viewholder.templates;

import com.google.firebase.Timestamp;

public interface TemplateListViewHolder {
    void setTitle(String title);
    void setDaysToFreeze(long daysToFreeze);
    void setQuantityOfVisits(long quantityOfVisits);
    void setQuantityToSkip(long quantityToSkip);
    void setExpiryDate(Timestamp expiryDate);
    void setStartDate(Timestamp startDate);
    void setUnlimited(boolean unlimited);
}
