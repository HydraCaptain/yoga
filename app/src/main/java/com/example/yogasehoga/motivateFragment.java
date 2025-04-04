package com.example.yogasehoga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.security.keystore.KeyInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class motivateFragment extends Fragment {
Button button;
String name, gender, mobile, email;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_motivate, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
            name = bundle.getString("name");
            gender = bundle.getString("gender");
            mobile = bundle.getString("mobile");
            email = bundle.getString("email");




        List<LinearLayout> linearLayouts = Arrays.asList(
                view.findViewById(R.id.lyoung),
                view.findViewById(R.id.lstress),
                view.findViewById(R.id.lhealth),
                view.findViewById(R.id.lflexibility),
                view.findViewById(R.id.lbetter),
                view.findViewById(R.id.lfocus),
                view.findViewById(R.id.lenergy)
        );

        List<RadioButton> radioButtons = Arrays.asList(
                view.findViewById(R.id.young),
                view.findViewById(R.id.stress),
                view.findViewById(R.id.health),
                view.findViewById(R.id.flexibility),
                view.findViewById(R.id.better),
                view.findViewById(R.id.focus),
                view.findViewById(R.id.energy)
        );
        Lcilck.setupLinearLayoutClick(linearLayouts, radioButtons);

        button = view.findViewById(R.id.button3);
        button.setOnClickListener(v -> {
            String selectedValue = null;

            for (RadioButton radioButton : radioButtons) {
                if (radioButton.isChecked()) {
                    selectedValue = radioButton.getTag().toString();
                    break;
                }
            }

            if (selectedValue == null) {
                Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            Bundle bundle1 = new Bundle();

            String motivation = selectedValue;

            bundle1.putString("name", name);
            bundle1.putString("gender", gender);
            bundle1.putString("mobile", mobile);
            bundle1.putString("email", email);
            bundle1.putString("motivation", motivation);

            maingoalFragment nextFragment = new maingoalFragment();
            nextFragment.setArguments(bundle1);

//            ((fragholdersign) requireActivity()).replaceFragment(nextFragment);
            assert getFragmentManager() != null;
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainhold, nextFragment)
                    .addToBackStack(null)
                    .commit();
        });

    }

}