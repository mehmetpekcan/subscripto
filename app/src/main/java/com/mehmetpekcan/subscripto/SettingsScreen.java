package com.mehmetpekcan.subscripto;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.Toast.LENGTH_SHORT;

public class SettingsScreen extends Fragment {
 String name, surname, email, password;
 EditText inputName, inputSurname, inputEmail, inputPassword;
 Button buttonChangeSettings;

 public SettingsScreen() { }

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  View settingsFragment = inflater.inflate(R.layout.fragment_settings_screen, container, false);



  return settingsFragment;
 }

 @Override
 public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
  super.onViewCreated(view, savedInstanceState);

  getClientInfos();

  inputName = getActivity().findViewById(R.id.inputName);
  inputSurname = getActivity().findViewById(R.id.inputSurname);
  inputEmail = getActivity().findViewById(R.id.inputEmail);
  inputPassword = getActivity().findViewById(R.id.inputPassword);
  buttonChangeSettings = getActivity().findViewById(R.id.buttonChangeSettings);

  setClientInfos();

  buttonChangeSettings.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
    try {
     SharedPreferences.Editor clientEditor = getContext().getSharedPreferences("clientPref", MODE_PRIVATE).edit();
     clientEditor.putString("name", inputName.getText().toString());
     clientEditor.putString("surname", inputSurname.getText().toString());
     clientEditor.putString("password", inputPassword.getText().toString());
     clientEditor.putString("email", inputEmail.getText().toString());
     clientEditor.commit();
     Toast.makeText(getActivity(), "Settings changed successfuly", LENGTH_SHORT).show();
     getClientInfos();
    } catch (Exception e) {
     Toast.makeText(getActivity(), "Settings couldn't changed", LENGTH_SHORT).show();
    }
   }
  });
 }

 public void getClientInfos() {
  SharedPreferences clientPref = getActivity().getSharedPreferences("clientPref", MODE_PRIVATE);
  email = clientPref.getString("email", "");
  password = clientPref.getString("password", "");
  name = clientPref.getString("name", "John");
  surname = clientPref.getString("surname", "Doe");
 }

 public void setClientInfos() {
  inputName.setText(name);
  inputPassword.setText(password);
  inputSurname.setText(surname);
  inputEmail.setText(email);
 }
}