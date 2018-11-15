package com.example.pratik.kaushalyaproto.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratik.kaushalyaproto.Classes.Course;
import com.example.pratik.kaushalyaproto.CourseInfoActivity;
import com.example.pratik.kaushalyaproto.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllCoursesAdapter extends RecyclerView.Adapter<AllCoursesAdapter.AllCoursesViewHolder> {

    private Context mContext;
    private List<Course> courselist;

    public AllCoursesAdapter(Context mContext, List<Course> courselist) {
        this.mContext = mContext;
        this.courselist = courselist;
    }

    @NonNull
    @Override
    public AllCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.cardlayout_allcourses, viewGroup, false);

        AllCoursesViewHolder myCoursesViewHolder = new AllCoursesViewHolder(view);
        return myCoursesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllCoursesViewHolder allCoursesViewHolder, int i) {

        final Course course = courselist.get(i);

        ImageView imageView = allCoursesViewHolder.imageView;
        TextView coursename, coursecategory, courserating;
        Button continueButton;

        coursename = allCoursesViewHolder.coursename;
        coursecategory = allCoursesViewHolder.coursecategory;
        courserating = allCoursesViewHolder.courserating;
        continueButton = allCoursesViewHolder.continueButton;

        coursename.setText(course.getName());
        coursecategory.setText(course.getCategory());
        courserating.setText(course.getRating());
        Uri uri = Uri.parse(course.getImageurl());

        Picasso.get()
                .load(uri)
                .into(allCoursesViewHolder.imageView);

        allCoursesViewHolder.imageView.setImageURI(uri);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, CourseInfoActivity.class);
                i.putExtra("object", course);
                mContext.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return courselist.size();
    }

    public class AllCoursesViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView coursename, coursecategory, courserating;
        Button continueButton;

        public AllCoursesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.courseimage);
            coursename = itemView.findViewById(R.id.coursenametext);
            coursecategory = itemView.findViewById(R.id.coursecategorytext);
            courserating = itemView.findViewById(R.id.courseratingtext);
            continueButton = itemView.findViewById(R.id.btn_continue);

        }
    }
}
