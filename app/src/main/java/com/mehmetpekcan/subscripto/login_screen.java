package com.mehmetpekcan.subscripto;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class login_screen extends Fragment {
    TextView goRegisterField;
    Button inputSignIn;

    public login_screen() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_login_screen, container, false);

        goRegisterField = fragmentView.findViewById(R.id.goRegisterField);
        inputSignIn = fragmentView.findViewById(R.id.inputSignIn);

        goRegisterField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(fragmentView).navigate(login_screenDirections.actionLoginScreenToRegister());
            }
        });

        inputSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragmentView.getContext(), MainActivity.class);
                fragmentView.getContext().startActivity(intent);
            }
        });

        return fragmentView;
    }
}