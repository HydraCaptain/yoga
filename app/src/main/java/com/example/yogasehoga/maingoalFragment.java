package com.example.yogasehoga;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

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

public class maingoalFragment extends Fragment {

Button button;
String motivation,name, gender, mobile, email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maingoal, container, false);
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle1 = this.getArguments();
                name = bundle1.getString("name");
                gender = bundle1.getString("gender");
                mobile = bundle1.getString("mobile");
                email =  bundle1.getString("email");
                motivation = bundle1.getString("motivation");

                Log.d("maingoalFragment", "Name: " + name+"gender"+gender+"mobile"+mobile+"email"+email+"motivation"+motivation);


        List<LinearLayout> linearLayouts = Arrays.asList(
                view.findViewById(R.id.llooseweight),
                view.findViewById(R.id.lkeepfit),
                view.findViewById(R.id.lrelax),
                view.findViewById(R.id.lflexible),
                view.findViewById(R.id.lrecover)
        );

        List<RadioButton> radioButtons = Arrays.asList(
                view.findViewById(R.id.looseweight),
                view.findViewById(R.id.keepfit),
                view.findViewById(R.id.relax),
                view.findViewById(R.id.flexible),
                view.findViewById(R.id.recover)
        );
        Lcilck.setupLinearLayoutClick(linearLayouts, radioButtons);

        button = view.findViewById(R.id.button4);
        button.setOnClickListener(v -> {
            String selectedValue = null;

            for (RadioButton radioButton : radioButtons) {
                if (radioButton.isChecked()) {
                    selectedValue = radioButton.getTag().toString();
                }
            }

            if (selectedValue == null) {
                Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            Bundle bundle2 = new Bundle();

            bundle2.putString("name", name);
            bundle2.putString("gender", gender);
            bundle2.putString("mobile", mobile);
            bundle2.putString("email", email);
            bundle2.putString("motivation", motivation);
            bundle2.putString("maingoal", selectedValue);

            bodydata nextFragment1 = new bodydata();
            nextFragment1.setArguments(bundle2);

//            ((fragholdersign) requireActivity()).replaceFragment(nextFragment1);
            assert getFragmentManager() != null;
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainhold, nextFragment1)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
