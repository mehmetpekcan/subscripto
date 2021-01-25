package com.mehmetpekcan.subscripto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.Toast.LENGTH_SHORT;

public class login_screen extends Fragment {
    private FirebaseAuth firebaseAuth;
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

        firebaseAuth = FirebaseAuth.getInstance();

        /*
        * If there is a logged in user, just logged in by default
        * */
        FirebaseUser signedInUser = firebaseAuth.getCurrentUser();
        if (signedInUser != null) {
            Intent intent = new Intent(fragmentView.getContext(), MainActivity.class);
            fragmentView.getContext().startActivity(intent);
            getActivity().finish();
        }

        goRegisterField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(fragmentView).navigate(login_screenDirections.actionLoginScreenToRegister());
            }
        });

        inputSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String enteredEmail = inputEmail.getText().toString();
                String enteredPassword = inputPassword.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(enteredEmail, enteredPassword)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getActivity(), "Sign in successfully", LENGTH_SHORT).show();
                        Intent intent = new Intent(fragmentView.getContext(), MainActivity.class);
                        fragmentView.getContext().startActivity(intent);
                        getActivity().finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getLocalizedMessage(), LENGTH_SHORT).show();
                    }
                });


            }
        });

        return fragmentView;
    }
}