package com.mehmetpekcan.subscripto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static com.mehmetpekcan.subscripto.MainScreen.subscriptionAdapter;
import static com.mehmetpekcan.subscripto.MainScreen.subscriptions;

public class NewSubscriptionScreen extends Fragment {
 EditText inputSubscriptionName, inputCost, inputDate, inputDescription;
 Button buttonNewSubscription;

 public NewSubscriptionScreen() { }

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
 }

 @Override
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
  Subscription newSubscription = new Subscription(
          R.drawable.youtube,
          inputDate.getText().toString(),
          inputSubscriptionName.getText().toString(),
          inputCost.getText().toString(),
          inputDescription.getText().toString());
  Log.d("asd", newSubscription.getDescription());
  subscriptions.add(newSubscription);
  subscriptionAdapter.notifyDataSetChanged();
 }
}