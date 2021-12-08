package com.example.kenko.Teacher.Manage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.kenko.R;
import com.example.kenko.models.StudentModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {

    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private IClickItemStudentListener iClickItemStudentListener;

    private List<StudentModel>students;
    private Context context;

    public StudentsAdapter(List<StudentModel> students, Context context, IClickItemStudentListener listener) {
        this.students = students;
        this.context = context;
        this.iClickItemStudentListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_manage_member_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StudentModel student = students.get(position);

        viewBinderHelper.bind(holder.swipeRevealLayout, student.getEmail());
        viewBinderHelper.setOpenOnlyOne(true);

        holder.nameMember.setText(student.getLastname() + " " + student.getFirstname());

        if (student.getStatus_img().equals("ok")){
            Glide.with(holder.img_member.getContext()).load("http://192.168.1.7/KenKo_PHP/upload/"+students.get(position).getEmail() + ".jpg").into(holder.img_member);
        }else{
            Glide.with(holder.img_member.getContext()).load("http://192.168.1.7/KenKo_PHP/upload/default.jpg").into(holder.img_member);
        }

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemStudentListener.deleteItemStudent(student);
                students.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        holder.img_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemStudentListener.detailItemStudent(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameMember;
        private CircleImageView img_member;
        private ImageView img_delete;
        private ImageView img_detail;

        private SwipeRevealLayout swipeRevealLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameMember = itemView.findViewById(R.id.nameMember);
            img_member = itemView.findViewById(R.id.img_member);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_detail = itemView.findViewById(R.id.img_detail);
            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
        }
    }
}
