package com.example.kenko.Student.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenko.R;
import com.example.kenko.models.SearchModel;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private IClickItemCourcesListener iClickItemCourceListener;

    private List<SearchModel>searchs;
    private Context context;

    public SearchAdapter(List<SearchModel> searchs, Context context, IClickItemCourcesListener listener) {
        this.searchs = searchs;
        this.context = context;
        this.iClickItemCourceListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_search_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchModel search = searchs.get(position);

        holder.name_cource.setText(search.getName_cource());
        holder.name_teacher.setText(search.getFirstname() + " " + search.getLastname());
        holder.descript_cource.setText(search.getDescript_cource());

        holder.main_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemCourceListener.detailItemCource(search);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchs.size();
    }

    public void filterList(List<SearchModel> filteredList){
        searchs = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout main_content;
        TextView name_cource;
        TextView name_teacher;
        TextView descript_cource;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            main_content = itemView.findViewById(R.id.main_content);
            name_cource = itemView.findViewById(R.id.name_cource);
            name_teacher = itemView.findViewById(R.id.name_teacher);
            descript_cource = itemView.findViewById(R.id.descript_cource);
        }
    }
}
