package com.mehmetpekcan.subscripto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;

public class subscriptionEdit extends Fragment {
 public ArrayList<Subscription> subscriptions = MainScreen.subscriptions;
 EditText inputName, inputCost, inputDate, inputDescription;
 Button buttonChange, buttonDelete;
 Integer subscriptionId;
 ImageView iwLogo;
 private FirebaseFirestore firebaseFirestore;
 String docId;

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

  firebaseFirestore = FirebaseFirestore.getInstance();
  final CollectionReference colRef = firebaseFirestore.collection("subscriptions");

  subscriptionId =  SubscriptionDetailScreenArgs.fromBundle(getArguments()).getSubscriptionId();
  docId = subscriptions.get(subscriptionId).getId();


  colRef.get()
          .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
            for (QueryDocumentSnapshot document : task.getResult()) {
             if (document.getId() == docId) {
              inputName.setText((String) document.get("name"));
              inputCost.setText((String) document.get("price"));
              inputDate.setText((String) document.get("date"));
              inputDescription.setText((String) document.get("description"));
              iwLogo.setImageResource(Integer.parseInt(String.valueOf(Objects.requireNonNull(document.get("image")))));
             }
            }
           }
          });

  buttonChange.setOnClickListener(new View.OnClickListener() {
   public void onClick(View view) {
    try {
     colRef.document(docId).update("name", inputName.getText().toString());
     colRef.document(docId).update("date", inputDate.getText().toString());
     colRef.document(docId).update("price", inputCost.getText().toString());
     colRef.document(docId).update("description", inputDescription.getText().toString());

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