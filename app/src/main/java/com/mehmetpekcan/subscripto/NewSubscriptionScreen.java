package com.mehmetpekcan.subscripto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class NewSubscriptionScreen extends Fragment {
 EditText inputSubscriptionName, inputCost, inputDate, inputDescription;
 Button buttonNewSubscription;

 public NewSubscriptionScreen() { }

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
 }

 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  View fragmentView = inflater.inflate(R.layout.fragment_new_subscription_screen, container, false);

  inputSubscriptionName = fragmentView.findViewById(R.id.inputSubscriptionName);
  inputCost = fragmentView.findViewById(R.id.inputCost);
  inputDate = fragmentView.findViewById(R.id.inputDate);
  inputDescription = fragmentView.findViewById(R.id.inputDescription);
  buttonNewSubscription = fragmentView.findViewById(R.id.buttonNewSubscription);

  buttonNewSubscription.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    handleNewSubscription();
   }
  });

  return fragmentView;
 }

 public void handleNewSubscription() {
  String subsName = inputSubscriptionName.getText().toString();
  Integer imageResource = 0;

  /* Image view will be dynamic after Firebase initializing */
  if (subsName.contains("Netflix")) {
   imageResource = R.drawable.netflix;
  } else if (subsName.contains("Youtube")) {
   imageResource = R.drawable.youtube;
  }

  Subscription newSubscription = new Subscription(
          imageResource,
          inputDate.getText().toString(),
          subsName,
          inputCost.getText().toString(),
          inputDescription.getText().toString());

  /* Add to shared preferences */
  SharedPreferences sharedPreferences = getActivity().getSharedPreferences("taskStore", Context.MODE_PRIVATE);
  SharedPreferences.Editor editor = sharedPreferences.edit();

  ArrayList<Subscription> list = new ArrayList<>();
  String isExist = sharedPreferences.getString("subscriptions", null);

  try {
   if (isExist != null) {
    Type type = new TypeToken<ArrayList<Subscription>>() {}.getType();
    list = new Gson().fromJson(sharedPreferences.getString("subscriptions", null), type);
   }

   list.add(newSubscription);
   editor.putString("subscriptions", new Gson().toJson(list)).apply();
   Toast.makeText(getActivity(), "New subscription added successfuly", LENGTH_SHORT).show();
   inputSubscriptionName.setText("");
   inputCost.setText("");
   inputDescription.setText("");
   inputDate.setText("");
  } catch (Exception ex) {
   Toast.makeText(getActivity(), "New subscription couldn't add", LENGTH_SHORT).show();
  }
  }
}