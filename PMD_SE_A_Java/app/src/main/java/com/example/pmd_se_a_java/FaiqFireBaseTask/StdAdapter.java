package com.example.pmd_se_a_java.FaiqFireBaseTask;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmd_se_a_java.FireBase_Task.Student;
import com.example.pmd_se_a_java.R;

import java.text.BreakIterator;
import java.util.List;

public class StdAdapter extends RecyclerView.Adapter<com.example.pmd_se_a_java.FaiqFireBaseTask.StdAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private com.example.pmd_se_a_java.FireBase_Task.StudentAdapter.OnItemClickListener listener;

    public List<Student> getStudentList() {
        return studentList;
    }
    public StdAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    // Define interface for click events
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Method to set click listener
    public void setOnItemClickListener(com.example.pmd_se_a_java.FireBase_Task.StudentAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public com.example.pmd_se_a_java.FaiqFireBaseTask.StdAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new com.example.pmd_se_a_java.FaiqFireBaseTask.StdAdapter.StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.pmd_se_a_java.FaiqFireBaseTask.StdAdapter.StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.textViewName.setText(student.getName());
        holder.textViewRollNo.setText(student.getRollNo());
        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRollNo;
        TextView textViewName;

        StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewRollNo=itemView.findViewById(R.id.textViewRollNo);
        }
    }
}
