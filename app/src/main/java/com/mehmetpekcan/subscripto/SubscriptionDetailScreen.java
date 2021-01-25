package com.mehmetpekcan.subscripto;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class SubscriptionDetailScreen extends Fragment {
 public ArrayList<Subscription> subscriptions = MainScreen.subscriptions;
 public TextView twDescription, twCost, twDate;
 public ImageView iwLogo;
 Button buttonPaid, buttonEdit;
 Integer isPaid, subscriptionId;
 private FirebaseFirestore firebaseFirestore;
 String docId;

 public SubscriptionDetailScreen() { }

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);

 }

 public void onViewCreated(View view, Bundle savedInstanceState) {
  super.onViewCreated(view, savedInstanceState);

  twDescription = getActivity().findViewById(R.id.twDescription);
  twCost = getActivity().findViewById(R.id.twCost);
  twDate = getActivity().findViewById(R.id.twDate);
  iwLogo = getActivity().findViewById(R.id.iwLogo);
  buttonPaid = getActivity().findViewById(R.id.buttonPaid);
  buttonEdit = getActivity().findViewById(R.id.buttonEdit);
  firebaseFirestore = FirebaseFirestore.getInstance();
  final CollectionReference colRef = firebaseFirestore.collection("subscriptions");


  /*
  * API call will be implement here to take subs. detail
  * */
  if (getArguments() != null) {
   subscriptionId =  SubscriptionDetailScreenArgs.fromBundle(getArguments()).getSubscriptionId();

   docId = subscriptions.get(subscriptionId).getId();

    colRef.get()
       .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
          for (QueryDocumentSnapshot document : task.getResult()) {
           if (document.getId() == docId) {
            twCost.setText((String) document.get("price"));
            twDate.setText((String) document.get("date"));
            twDescription.setText((String) document.get("description"));
            iwLogo.setImageResource(Integer.parseInt(String.valueOf(Objects.requireNonNull(document.get("image")))));
            isPaid = Integer.parseInt(String.valueOf(document.get("isPaid")));

            if (isPaid == 0) {
             buttonPaid.setBackgroundResource(R.color.RED);
             buttonPaid.setText("Unpaid");
            } else {
             buttonPaid.setBackgroundResource(R.color.PRIMARY_COLOR);
             buttonPaid.setText("Paid");
            }
           }
          }
        }
       });
  }

  buttonPaid.setOnClickListener(new View.OnClickListener() {
   public void onClick(View view) {

    if (isPaid == 1) {
     colRef.document(docId).update("isPaid", 0);
     buttonPaid.setBackgroundResource(R.color.RED);
     buttonPaid.setText("Unpaid");
    } else {
     colRef.document(docId).update("isPaid", 1);
     buttonPaid.setBackgroundResource(R.color.PRIMARY_COLOR);
     buttonPaid.setText("Paid");
    }
   }
  });

  buttonEdit.setOnClickListener(new View.OnClickListener() {
   public void onClick(View view) {
    Navigation.findNavController(view).navigate(SubscriptionDetailScreenDirections.actionSubscriptionDetailScreenToSubscriptionEdit(subscriptionId));
   }
  });

 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  return inflater.inflate(R.layout.fragment_subscription_detail_screen, container, false);
 }
}