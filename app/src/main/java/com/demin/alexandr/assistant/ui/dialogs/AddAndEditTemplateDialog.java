package com.demin.alexandr.assistant.ui.dialogs;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demin.alexandr.assistant.App;
import com.demin.alexandr.assistant.R;
import com.demin.alexandr.assistant.mvp.model.auth.Authentication;
import com.demin.alexandr.assistant.mvp.model.entity.Template;
import com.demin.alexandr.assistant.mvp.model.parser.DateParser;
import com.demin.alexandr.assistant.mvp.model.repository.Repository;
import com.demin.alexandr.assistant.mvp.presentation.fragments.teacher.TemplatePresenter;
import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_DAYS_TO_FREEZE_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_EXPIRY_DATE_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_ID_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_QUANTITY_OF_VISITS_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_QUANTITY_TO_SKIP_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_START_DATE_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_TITLE_FIELD_KEY;
import static com.demin.alexandr.assistant.mvp.model.entity.Template.TEMPLATE_UNLIMITED_FIELD_KEY;

public class AddAndEditTemplateDialog extends BottomSheetDialogFragment {

    private static final String HEADLINE_FIELD_KEY = "headline";

    @BindView(R.id.dialog_add_edit_template_main_container)
    LinearLayout mainContainer;
    @BindView(R.id.dialog_add_edit_template_calendar_container)
    LinearLayout calendarContainer;
    @BindView(R.id.dialog_add_edit_template_headline)
    TextView headline;
    @BindView(R.id.dialog_add_edit_template_title)
    TextInputEditText title;
    @BindView(R.id.dialog_add_edit_template_start_date_container)
    RelativeLayout startDateContainer;
    @BindView(R.id.dialog_add_edit_template_start_date_text)
    TextView startDateText;
    @BindView(R.id.dialog_add_edit_template_expiry_date_container)
    RelativeLayout expiryDateContainer;
    @BindView(R.id.dialog_add_edit_template_expiry_date_text)
    TextView expiryDateText;
    @BindView(R.id.dialog_add_edit_template_quantity_of_visits_text)
    TextInputEditText quantityOfVisits;
    @BindView(R.id.dialog_add_edit_template_days_to_freeze)
    TextInputEditText daysToFreeze;
    @BindView(R.id.dialog_add_edit_template_quantity_to_skip)
    TextInputEditText quantityToSkip;
    @BindView(R.id.dialog_add_edit_template_quantity_of_visits_unlimited)
    CheckBox unlimitedVisits;
    @BindView(R.id.dialog_add_edit_template_calendar)
    CalendarView calendarView;
    @BindView(R.id.dialog_add_edit_template_main_btn_ok)
    MaterialButton okMainButton;
    @BindView(R.id.dialog_add_edit_template_calendar_btn_ok)
    MaterialButton okCalendarButton;
    @BindView(R.id.dialog_add_edit_template_calendar_btn_cancel)
    MaterialButton cancelCalendarButton;

    @Inject
    @Named("firebase")
    Repository firebaseRepository;
    @Inject
    Authentication firebaseAuthentication;
    @Inject
    @Named("mainThread")
    Scheduler mainThread;
    @Inject
    DateParser dateParser;

    private BottomSheetBehavior bottomSheetBehavior;
    private boolean isStartDatePressed;
    private String dateFromCalendar;
    private TemplatePresenter templatePresenter;
    private String templateId;

    public void setTemplatePresenter(TemplatePresenter templatePresenter) {
        this.templatePresenter = templatePresenter;
    }

    public static AddAndEditTemplateDialog newInstance(String headline) {
        AddAndEditTemplateDialog fragment = new AddAndEditTemplateDialog();
        Bundle bundle = new Bundle();
        bundle.putString(HEADLINE_FIELD_KEY, headline);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static AddAndEditTemplateDialog newInstance(String headline, Template template) {
        String dateFormatPattern = "dd.MM.yy";
        AddAndEditTemplateDialog fragment = new AddAndEditTemplateDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TEMPLATE_ID_KEY, template.getId());
        bundle.putString(HEADLINE_FIELD_KEY, headline);
        bundle.putString(TEMPLATE_TITLE_FIELD_KEY, template.getTitle());
        bundle.putBoolean(TEMPLATE_UNLIMITED_FIELD_KEY, template.isUnlimited());
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern, Locale.getDefault());
        bundle.putString(TEMPLATE_EXPIRY_DATE_FIELD_KEY,
                template.getExpiryDate() == null ? "" : dateFormat.format(template.getExpiryDate().toDate()));
        bundle.putString(TEMPLATE_START_DATE_FIELD_KEY,
                template.getStartDate() == null ? "" : dateFormat.format(template.getStartDate().toDate()));
        bundle.putLong(TEMPLATE_DAYS_TO_FREEZE_FIELD_KEY, template.getDaysToFreeze());
        bundle.putLong(TEMPLATE_QUANTITY_OF_VISITS_FIELD_KEY, template.getQuantityOfVisits());
        bundle.putLong(TEMPLATE_QUANTITY_TO_SKIP_FIELD_KEY, template.getQuantityToSkip());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.fragment_add_edit_template_dialog, null);
        ButterKnife.bind(this, view);
        dialog.setContentView(view);
        App.getInstance().getAppComponent().inject(AddAndEditTemplateDialog.this);
        initViews();
        initListeners();
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        return dialog;
    }

    private void initViews() {
        mainContainer.setVisibility(View.VISIBLE);
        calendarContainer.setVisibility(View.GONE);
        if (getArguments().getString(HEADLINE_FIELD_KEY, "").equals(getString(R.string.headline_edit_template))) {
            inflateViews();
        } else {
            inflateDates();
        }
    }

    private void initListeners() {
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> setDateFromCalendar(year, month, dayOfMonth));
    }

    private void toggleContainers() {
        if (mainContainer.getVisibility() == View.VISIBLE) {
            mainContainer.setVisibility(View.GONE);
            calendarContainer.setVisibility(View.VISIBLE);
        } else {
            mainContainer.setVisibility(View.VISIBLE);
            calendarContainer.setVisibility(View.GONE);
        }
    }

    private void toggleQuantityOfVisitsClickable() {
        // TODO: 10.04.2019
        if (unlimitedVisits.isChecked()) {
            quantityOfVisits.setEnabled(false);
        } else {
            quantityOfVisits.setEnabled(true);
        }
    }

    private void inflateViews() {
        Bundle bundle = getArguments();
        templateId = bundle.getString(TEMPLATE_ID_KEY, "");
        headline.setText(bundle.getString(HEADLINE_FIELD_KEY, ""));
        title.setText(bundle.getString(TEMPLATE_TITLE_FIELD_KEY, ""));
        unlimitedVisits.setChecked(bundle.getBoolean(TEMPLATE_UNLIMITED_FIELD_KEY, false));
        expiryDateText.setText(bundle.getString(TEMPLATE_EXPIRY_DATE_FIELD_KEY, ""));
        startDateText.setText(bundle.getString(TEMPLATE_START_DATE_FIELD_KEY, ""));
        daysToFreeze.setText(String.valueOf(bundle.getLong(TEMPLATE_DAYS_TO_FREEZE_FIELD_KEY, 0L)));
        quantityOfVisits.setText(String.valueOf(bundle.getLong(TEMPLATE_QUANTITY_OF_VISITS_FIELD_KEY, 0L)));
        quantityToSkip.setText(String.valueOf(bundle.getLong(TEMPLATE_QUANTITY_TO_SKIP_FIELD_KEY, 0L)));
        toggleQuantityOfVisitsClickable();
    }

    private void inflateDates() {
        long millisecondsInDay = 86400000L;
        Date currentDate = new Date();
        Date weekLaterDate = new Date(currentDate.getTime() + millisecondsInDay * 7);
        startDateText.setText(dateParser.dateToString(currentDate));
        expiryDateText.setText(dateParser.dateToString(weekLaterDate));
    }

    private boolean isAddTemplateDialog() {
        return getArguments().getString(HEADLINE_FIELD_KEY, "").equals(getString(R.string.headline_add_new_template));
    }

    private boolean isFieldsFill() {
        String error = getString(R.string.empty_field);
        boolean isFieldsFill = true;
        if (title.getText() == null || title.getText().toString().equals("")) {
            title.setError(error);
            isFieldsFill = false;
        }
        if (daysToFreeze.getText() == null || daysToFreeze.getText().toString().equals("")) {
            daysToFreeze.setError(error);
            isFieldsFill = false;
        }
        if (quantityOfVisits.getText() == null || quantityOfVisits.getText().toString().equals("")) {
            if (unlimitedVisits.isChecked()) {
                quantityOfVisits.setText(0);
            } else {
                quantityOfVisits.setError(error);
                isFieldsFill = false;
            }
        }
        if (quantityToSkip.getText() == null || quantityToSkip.getText().toString().equals("")) {
            quantityToSkip.setError(error);
            isFieldsFill = false;
        }
        return isFieldsFill;
    }

    private void setDateFromCalendar(int year, int month, int dayOfMonth) {
        dateFromCalendar = String.format(Locale.getDefault(), "%02d.%02d.%d", dayOfMonth, month + 1, year);
    }

    @OnClick(R.id.dialog_add_edit_template_start_date_container)
    public void startDatePressed() {
        isStartDatePressed = true;
        dateFromCalendar = null;
        calendarView.setDate(dateParser.stringToDate(startDateText.getText().toString()).getTime());
        toggleContainers();
    }

    @OnClick(R.id.dialog_add_edit_template_expiry_date_container)
    public void expiryDatePressed() {
        isStartDatePressed = false;
        dateFromCalendar = null;
        calendarView.setDate(dateParser.stringToDate(expiryDateText.getText().toString()).getTime());
        toggleContainers();
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.dialog_add_edit_template_main_btn_ok)
    public void okMainContainerPressed() {
        if (isFieldsFill()) {
            Template template = new Template();
            firebaseAuthentication.getCurrentUser()
                    .flatMapCompletable(user -> {
                        template.setOwnerId(user.getUid());
                        template.setId(templateId);
                        template.setUnlimited(unlimitedVisits.isChecked());
                        template.setStartDate(new Timestamp(dateParser.stringToDate(startDateText.getText().toString())));
                        template.setExpiryDate(new Timestamp(dateParser.stringToDate(expiryDateText.getText().toString())));
                        template.setTitle(title.getText().toString());
                        template.setDaysToFreeze(Long.parseLong(daysToFreeze.getText().toString()));
                        template.setQuantityOfVisits(Long.parseLong(quantityOfVisits.getText().toString()));
                        template.setQuantityToSkip(Long.parseLong(quantityToSkip.getText().toString()));
                        if (isAddTemplateDialog()) {
                            return firebaseRepository.addNewTemplate(template);
                        }
                        return firebaseRepository.updateTemplateData(template);
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(mainThread)
                    .subscribe(() -> {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        templatePresenter.loadTemplates();
                    }, throwable -> {
/*                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        templatePresenter.loadTemplates();*/
                        // TODO: 02.04.2019
                    });
        }
    }

    @OnClick(R.id.dialog_add_edit_template_calendar_btn_ok)
    public void okCalendarContainerPressed() {
        if (isStartDatePressed) {
            if (dateFromCalendar != null) {
                startDateText.setText(dateFromCalendar);
            }
        } else {
            if (dateFromCalendar != null) {
                expiryDateText.setText(dateFromCalendar);
            }
        }
        toggleContainers();
    }

    @OnClick(R.id.dialog_add_edit_template_calendar_btn_cancel)
    public void cancelCalendarContainerPressed() {
        toggleContainers();
    }

    @OnCheckedChanged(R.id.dialog_add_edit_template_quantity_of_visits_unlimited)
    public void unlimitedCheckBoxChecked(CompoundButton buttonView, boolean isChecked) {
        toggleQuantityOfVisitsClickable();
    }

}
