package com.example.pratik.kaushalyaproto.CoursesFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pratik.kaushalyaproto.Classes.Mycourse;
import com.example.pratik.kaushalyaproto.R;
import com.example.pratik.kaushalyaproto.adapters.MyCoursesAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyCoursesFragment extends Fragment {

    RecyclerView recyclerView;
    View view;
    ArrayList<Mycourse> courseList;
    MyCoursesAdapter myCoursesAdapter;

    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        mAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.fragment_mycourses, container, false);

        //setting up recyclerview
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        courseList = new ArrayList<>();

        db.collection("Users/" + mAuth.getCurrentUser().getUid() + "/" + "mycourses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {

                                Mycourse course = new Mycourse(document.getString("coursesid"), document.getString("status"));
                                courseList.add(course);
                            }

                            myCoursesAdapter = new MyCoursesAdapter(getContext(), courseList);
                            recyclerView.setAdapter(myCoursesAdapter);

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        return view;
    }
}
