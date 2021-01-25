package com.mehmetpekcan.subscripto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class subscriptionEdit extends Fragment {
 public ArrayList<Subscription> subscriptions = MainScreen.subscriptions;
 EditText inputName, inputCost, inputDate, inputDescription;
 Button buttonChange, buttonDelete;
 Integer subscriptionId;
 ImageView iwLogo;

 public subscriptionEdit() { }

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  View editFragment = inflater.inflate(R.layout.fragment_subscription_edit, container, false);

  inputName = editFragment.findViewById(R.id.inputName);
  inputCost = editFragment.findViewById(R.id.inputCost);
  inputDate = editFragment.findViewById(R.id.inputDate);
  inputDescription = editFragment.findViewById(R.id.inputDescription);
  iwLogo = editFragment.findViewById(R.id.iwLogo);
  buttonChange = editFragment.findViewById(R.id.buttonChange);
  buttonDelete = editFragment.findViewById(R.id.buttonDelete);

  subscriptionId =  SubscriptionDetailScreenArgs.fromBundle(getArguments()).getSubscriptionId();

  inputName.setText(subscriptions.get(subscriptionId).getBrand());
  inputCost.setText(subscriptions.get(subscriptionId).getPrice());
  inputDate.setText(subscriptions.get(subscriptionId).getDate());
  inputDescription.setText(subscriptions.get(subscriptionId).getDescription());
  iwLogo.setImageResource(subscriptions.get(subscriptionId).getLogo());


  buttonChange.setOnClickListener(new View.OnClickListener() {
   public void onClick(View view) {
    try {
     Subscription test = new Subscription(
             subscriptions.get(subscriptionId).getLogo(),
             inputDate.getText().toString(),
             inputName.getText().toString(),
             inputCost.getText().toString(),
             inputDescription.getText().toString(),
             0);

     subscriptions.get(subscriptionId).setBrand(inputName.getText().toString());
     subscriptions.get(subscriptionId).setPrice(inputCost.getText().toString());
     subscriptions.get(subscriptionId).setDate(inputDate.getText().toString());
     subscriptions.get(subscriptionId).setDescription(inputDescription.getText().toString());

     Toast.makeText(getActivity(), "Successfully changed!", LENGTH_SHORT).show();
     Navigation.findNavController(view).navigate(subscriptionEditDirections.actionSubscriptionEditToSubscriptionDetailScreen(subscriptionId));
    } catch (Exception ex) {
     Toast.makeText(getActivity(), "An error has occured", LENGTH_SHORT).show();
    }
   }
  });

  buttonDelete.setOnClickListener(new View.OnClickListener() {
   public void onClick(View view) {
    Navigation.findNavController(view).navigate(subscriptionEditDirections.actionSubscriptionEditToMainScreen());
   }
  });

  return editFragment;
 }
}