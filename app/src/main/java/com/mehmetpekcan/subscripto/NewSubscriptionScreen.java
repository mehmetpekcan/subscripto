package com.mehmetpekcan.subscripto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;


public class NewSubscriptionScreen extends Fragment {
 EditText inputSubscriptionName, inputCost, inputDate, inputDescription;
 Button buttonNewSubscription;
 private FirebaseFirestore firebaseFirestore;
 private FirebaseAuth firebaseAuth;

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

  firebaseFirestore = FirebaseFirestore.getInstance();
  firebaseAuth = FirebaseAuth.getInstance();

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

  FirebaseUser currentUser = firebaseAuth.getCurrentUser();

  HashMap<String, Object> saveIt = new HashMap<>();
  saveIt.put("image", imageResource);
  saveIt.put("date", inputDate.getText().toString());
  saveIt.put("name", subsName);
  saveIt.put("price", inputCost.getText().toString());
  saveIt.put("description", inputDescription.getText().toString());
  saveIt.put("userEmail", currentUser.getEmail());

  firebaseFirestore.collection("subscriptions").add(saveIt).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
    @Override
    public void onSuccess(DocumentReference documentReference) {
     Toast.makeText(getActivity(), "New subscription added successfuly", LENGTH_SHORT).show();

     inputSubscriptionName.setText("");
     inputCost.setText("");
     inputDescription.setText("");
     inputDate.setText("");
    }
   }).addOnFailureListener(new OnFailureListener() {
   @Override
   public void onFailure(@NonNull Exception e) {
    Toast.makeText(getActivity(), e.getLocalizedMessage(), LENGTH_SHORT).show();
   }
  });
  }
}