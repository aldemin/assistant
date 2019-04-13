package com.demin.alexandr.assistant.mvp.presentation.fragments.teacher;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.mvp.model.entity.Pass;
import com.demin.alexandr.assistant.mvp.view.fragments.teacher.PassView;
import com.demin.alexandr.assistant.recycle.viewholder.passes.PassListViewHolder;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class PassPresenter extends MvpPresenter<PassView> {

    @Inject
    Router router;
    @Inject
    FirebaseFirestore firestore;

    private List<Pass> passes = new ArrayList<>();
    private PassListPresenter passListPresenter = new PassListPresenter();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        App.getInstance().getAppComponent().inject(PassPresenter.this);
        inflatePasses();
    }

    private void inflatePasses() {
        // TODO: 05.03.2019
        this.passes.add(new Pass());
        this.passes.add(new Pass());
        this.passes.add(new Pass());
        this.passes.add(new Pass());
        this.passes.add(new Pass());
        getViewState().updatePassList();
    }

    public PassListPresenter getPassListPresenter() {
        return passListPresenter;
    }

    public void cameraFabPressed() {
        // TODO: 06.03.2019
    }

    public class PassListPresenter {

        public void bindList(int position, PassListViewHolder view) {
            if (passes != null) {
                Pass pass = passes.get(position);
                if (pass != null) {
                    // TODO: 06.03.2019
                }
            }
        }

        public int getCount() {
            return passes.size();
        }

        public void openRedactorDialog(int position) {
            // TODO: 06.03.2019
        }

        public void itemPressed() {

        }
    }
}
