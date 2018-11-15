package com.example.pratik.kaushalyaproto.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratik.kaushalyaproto.Classes.Mycourse;
import com.example.pratik.kaushalyaproto.R;

import java.util.List;

public class MyCoursesAdapter extends RecyclerView.Adapter<MyCoursesAdapter.MyCoursesViewHolder> {

    private Context mContext;
    private List<Mycourse> courselist;

    public MyCoursesAdapter(Context mContext, List<Mycourse> courselist) {
        this.mContext = mContext;
        this.courselist = courselist;
    }

    @NonNull
    @Override
    public MyCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.cardlayout_mycourse, viewGroup, false);

        MyCoursesViewHolder myCoursesViewHolder = new MyCoursesViewHolder(view);
        return myCoursesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCoursesViewHolder myCoursesViewHolder, int i) {


        Mycourse course = courselist.get(i);

        ImageView imageView = myCoursesViewHolder.imageView;
        TextView coursename, coursecategory, courserating;
        Button continueButton;

        coursename = myCoursesViewHolder.coursename;
        coursecategory = myCoursesViewHolder.coursecategory;
        courserating = myCoursesViewHolder.courserating;
        continueButton = myCoursesViewHolder.continueButton;

        coursename.setText(course.getCourseid());
        coursecategory.setText(course.getStatus());
        //courserating.setText(course.getRating());
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return courselist.size();
    }

    public class MyCoursesViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView coursename, coursecategory, courserating;
        Button continueButton;

        public MyCoursesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.courseimage);
            coursename = itemView.findViewById(R.id.coursenametext);
            coursecategory = itemView.findViewById(R.id.coursecategorytext);
            courserating = itemView.findViewById(R.id.courseratingtext);
            continueButton = itemView.findViewById(R.id.btn_continue);

        }
    }
}
