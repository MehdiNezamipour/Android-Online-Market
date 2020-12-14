package com.nezamipour.mehdi.digikala.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.nezamipour.mehdi.digikala.R;
import com.nezamipour.mehdi.digikala.databinding.FragmentNotificationSettingBinding;

public class NotificationSettingFragment extends Fragment {

    private FragmentNotificationSettingBinding mBinding;
    private Integer mHour = 3;


    public NotificationSettingFragment() {
        // Required empty public constructor
    }

    public static NotificationSettingFragment newInstance(String param1, String param2) {
        return new NotificationSettingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification_setting, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSpinner();

        mBinding.buttonChangeTiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        mBinding.editTextCustomTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mHour = Integer.parseInt((String) s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals(getString(R.string.per_3_hours))) {
                    mHour = 3;
                } else if (selectedItem.equals(getString(R.string.per_5_hours))) {
                    mHour = 5;

                } else if (selectedItem.equals(getString(R.string.per_8_hours))) {
                    mHour = 8;

                } else if (selectedItem.equals(getString(R.string.per_12_hours))) {
                    mHour = 12;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void initSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter =
                ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.time_items_array,
                        android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mBinding.spinner.setAdapter(arrayAdapter);
    }
}