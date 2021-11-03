package com.example.kenko.Teacher.Manage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.kenko.R;
import com.example.kenko.models.CourceModel;
import java.util.List;

public class CourceAdapter extends RecyclerView.Adapter<CourceAdapter.ViewHolder> {

    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private IClickItemCourceListener iClickItemCourceListener;
    private List<CourceModel>cources;
    private Context context;

    public CourceAdapter(List<CourceModel> cources, Context context, IClickItemCourceListener listener) {
        this.cources = cources;
        this.context = context;
        this.iClickItemCourceListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_manage_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CourceModel cource = cources.get(position);

        viewBinderHelper.bind(holder.swipeRevealLayout, cource.getIdCource());
        viewBinderHelper.setOpenOnlyOne(true);

        holder.codeCource.setText(cource.getIdCource());
        holder.nameCource.setText(cource.getNameCource());

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemCourceListener.deleteItemCource(cource);
                cources.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        holder.layoutDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemCourceListener.detailItemCource(cource);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cources.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SwipeRevealLayout swipeRevealLayout;
        private TextView codeCource;
        private TextView nameCource;

        private TextView layoutDetails;
        private TextView layoutDelete;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
            codeCource = itemView.findViewById(R.id.codeCource);
            nameCource = itemView.findViewById(R.id.nameCource);
            layoutDetails = itemView.findViewById(R.id.layoutDetails);
            layoutDelete = itemView.findViewById(R.id.layoutDelete);

        }
    }
}
