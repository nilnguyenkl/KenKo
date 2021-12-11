package com.example.kenko.Student.Manage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenko.R;
import com.example.kenko.models.ParticipantCourceModel;

import java.util.List;

public class CourceParticipantAdapter extends RecyclerView.Adapter<CourceParticipantAdapter.ViewHolder> {

    private IClickItemParticipantListener iClickItemParticipantListener;
    private List<ParticipantCourceModel> participants;
    private Context context;

    public CourceParticipantAdapter(List<ParticipantCourceModel> participants, Context context, IClickItemParticipantListener listener) {
        this.participants = participants;
        this.context = context;
        this.iClickItemParticipantListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_manage_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ParticipantCourceModel participant = participants.get(position);

        holder.name_courceS.setText(participant.getNameCource());
        holder.name_teacherS.setText(participant.getFirstname() + " " + participant.getLastname());
        holder.phone_teacherS.setText(participant.getPhone());
        holder.descript_courceS.setText(participant.getDescript_cource());

        holder.deleteCource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemParticipantListener.deleteItemCource(participant);
                participants.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    public void filterList(List<ParticipantCourceModel> filteredList){
        participants = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name_courceS;
        private TextView name_teacherS;

        private TextView phone_teacherS;
        private TextView descript_courceS;

        private Button deleteCource;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            name_courceS = itemView.findViewById(R.id.name_courceS);
            name_teacherS = itemView.findViewById(R.id.name_teacherS);
            phone_teacherS = itemView.findViewById(R.id.phone_teacherS);
            descript_courceS = itemView.findViewById(R.id.descript_courceS);
            deleteCource = itemView.findViewById(R.id.deleteCource);

        }
    }
}
