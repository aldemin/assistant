package com.demin.alexandr.assistant.recycle.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.presentation.PassPresenter;
import com.demin.alexandr.assistant.recycle.viewholder.PassListViewHolder;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PassListAdapter extends RecyclerView.Adapter<PassListAdapter.ViewHolder> {

    private PassPresenter.PassListPresenter passListPresenter;

    public PassListAdapter(PassPresenter.PassListPresenter passListPresenter) {
        this.passListPresenter = passListPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_pass, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        passListPresenter.bindProjectList(i, viewHolder);
    }

    @Override
    public int getItemCount() {
        return passListPresenter.getProjectsCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements PassListViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_pass_container)
        @Override
        public void itemPressed() {

        }
    }

}
