package com.demin.alexandr.assistant.recycle.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.model.entity.Template;
import com.demin.alexandr.assistant.mvp.presentation.fragments.teacher.TemplatePresenter;
import com.demin.alexandr.assistant.recycle.viewholder.templates.TemplateListViewHolder;
import com.google.firebase.Timestamp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        TextView quantityOfVisits;
        @BindView(R.id.item_template_quantity_to_skip)
        TextView quantityToSkip;
        @BindView(R.id.item_template_expiry_date)
        TextView expiryDate;
        @BindView(R.id.item_template_start_date)
        TextView startDate;
        @BindView(R.id.item_template_unlimited)
        TextView unlimited;
        @BindView(R.id.item_template_container)
        CardView container;

        private String ownerId;
        private String id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        @Override
        public void setId(String id) {
            this.id = id;
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
            this.quantityOfVisits.setText(String.valueOf(quantityOfVisits));
        }

        @Override
        public void setQuantityToSkip(long quantityToSkip) {
            this.quantityToSkip.setText(String.valueOf(quantityToSkip));
        }

        @Override
        public void setExpiryDate(Timestamp expiryDate) {
            this.expiryDate.setText(presenter.getDateParser().dateToString(expiryDate.toDate()));
        }

        @Override
        public void setStartDate(Timestamp startDate) {
            this.startDate.setText(presenter.getDateParser().dateToString(startDate.toDate()));
        }

        @Override
        public void setUnlimited(boolean unlimited) {
            this.unlimited.setText(String.valueOf(unlimited));
        }

        @OnClick(R.id.item_template_container)
        public void itemPressed() {
            Template template = new Template();
            template.setId(id);
            template.setOwnerId(ownerId);
            template.setUnlimited(Boolean.valueOf(unlimited.getText().toString()));
            template.setStartDate(new Timestamp(presenter.getDateParser().stringToDate(startDate.getText().toString())));
            template.setExpiryDate(new Timestamp(presenter.getDateParser().stringToDate(expiryDate.getText().toString())));
            template.setTitle(title.getText().toString());
            template.setDaysToFreeze(Long.parseLong(daysToFreeze.getText().toString()));
            template.setQuantityOfVisits(Long.parseLong(quantityOfVisits.getText().toString()));
            template.setQuantityToSkip(Long.parseLong(quantityToSkip.getText().toString()));
            presenter.itemPressed(template);
        }
    }
}
