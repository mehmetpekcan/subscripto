package com.mehmetpekcan.subscripto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.Toast.LENGTH_SHORT;

public class login_screen extends Fragment {
    TextView goRegisterField;
    Button inputSignIn;
    EditText inputEmail;
    EditText inputPassword;

    public login_screen() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_login_screen, container, false);

        goRegisterField = fragmentView.findViewById(R.id.goRegisterField);
        inputSignIn = fragmentView.findViewById(R.id.inputSignIn);
        inputEmail = fragmentView.findViewById(R.id.inputEmail);
        inputPassword = fragmentView.findViewById(R.id.inputPassword);

        goRegisterField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(fragmentView).navigate(login_screenDirections.actionLoginScreenToRegister());
            }
        });

        inputSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = inputEmail.getText().toString();
                String enteredPassword = inputPassword.getText().toString();
                SharedPreferences clientPref = getActivity().getSharedPreferences("clientPref", MODE_PRIVATE);
                String savedEmail = clientPref.getString("email", null);
                String savedPassword = clientPref.getString("password", null);

                try {
                    if (!enteredEmail.equals(savedEmail) || !enteredPassword.equals(savedPassword)) {
                        Toast.makeText(getActivity(), "Email or password is wrong...", LENGTH_SHORT).show();
                    }  else {
                        Intent intent = new Intent(fragmentView.getContext(), MainActivity.class);
                        fragmentView.getContext().startActivity(intent);
                    }
                } catch(Exception e) {
                    Toast.makeText(getActivity(), "An error has occurred", LENGTH_SHORT).show();
                }
            }
        });

        return fragmentView;
    }
}