package com.demin.alexandr.assistant.recycle.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.TemplatePresenter;
import com.demin.alexandr.assistant.recycle.viewholder.templates.TemplateListViewHolder;
import com.google.firebase.Timestamp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemplateListAdapter extends RecyclerView.Adapter<TemplateListAdapter.ViewHolder> {

    private TemplatePresenter.TemplatesListPresenter presenter;

    public TemplateListAdapter(TemplatePresenter.TemplatesListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public TemplateListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_template, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateListAdapter.ViewHolder viewHolder, int i) {
        presenter.bindList(i, viewHolder);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements TemplateListViewHolder {

        @BindView(R.id.item_template_title)
        TextView title;
        @BindView(R.id.item_template_days_to_freeze)
        TextView daysToFreeze;
        @BindView(R.id.item_template_quantity_of_visits)
        TextView quattityOfVisits;
        @BindView(R.id.item_template_quantity_to_skip)
        TextView quattityToSkip;
        @BindView(R.id.item_template_expiry_date)
        TextView expiryDate;
        @BindView(R.id.item_template_start_date)
        TextView startDate;
        @BindView(R.id.item_template_unlimited)
        TextView unlimited;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setTitle(String title) {
            this.title.setText(title);
        }

        @Override
        public void setDaysToFreeze(long daysToFreeze) {
            this.daysToFreeze.setText(String.valueOf(daysToFreeze));
        }

        @Override
        public void setQuantityOfVisits(long quantityOfVisits) {
            this.quattityOfVisits.setText(String.valueOf(quantityOfVisits));
        }

        @Override
        public void setQuantityToSkip(long quantityToSkip) {
            this.quattityToSkip.setText(String.valueOf(quantityToSkip));
        }

        @Override
        public void setExpiryDate(Timestamp expiryDate) {
            this.expiryDate.setText(expiryDate.toDate().toString());
        }

        @Override
        public void setStartDate(Timestamp startDate) {
            this.startDate.setText(startDate.toDate().toString());
        }

        @Override
        public void setUnlimited(boolean unlimited) {
            this.unlimited.setText(String.valueOf(unlimited));
        }
    }
}
