package com.mehmetpekcan.subscripto;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.Toast.LENGTH_SHORT;


public class register extends Fragment {
 Button buttonSignUp;
 EditText inputEmail, inputPassword, inputName, inputSurname;
 private FirebaseAuth firebaseAuth;

 public register() { }

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  return inflater.inflate(R.layout.fragment_register, container, false);
 }

 @Override
 public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
  super.onViewCreated(view, savedInstanceState);

  firebaseAuth = FirebaseAuth.getInstance();

  inputEmail = view.findViewById(R.id.inputEmail);
  inputPassword = view.findViewById(R.id.inputPassword);
  inputName = view.findViewById(R.id.inputName);
  inputSurname = view.findViewById(R.id.inputSurname);
  buttonSignUp = view.findViewById(R.id.buttonSignUp);

  buttonSignUp.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(final View view) {
    String enteredEmail = inputEmail.getText().toString();
    String enteredPassword = inputPassword.getText().toString();

    firebaseAuth.createUserWithEmailAndPassword(enteredEmail, enteredPassword)
            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
       @Override
       public void onSuccess(AuthResult authResult) {
        Toast.makeText(getActivity(), "Successfully registered!", LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(registerDirections.actionRegisterToLoginScreen());
       }
       }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
         Toast.makeText(getActivity(), e.getLocalizedMessage(), LENGTH_SHORT).show();
        }
       });
   }
  });
 }
}