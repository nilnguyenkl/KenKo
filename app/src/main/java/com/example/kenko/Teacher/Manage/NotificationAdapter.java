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
import com.example.kenko.models.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private IClickItemNotificationListener iClickItemNotificationListener;

    private List<NotificationModel>notifications;
    private Context context;

    public NotificationAdapter(List<NotificationModel> notifications, Context context, IClickItemNotificationListener listener) {
        this.notifications = notifications;
        this.context = context;
        this.iClickItemNotificationListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_manage_notification_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationModel notification = notifications.get(position);

        viewBinderHelper.bind(holder.swipeRevealLayout, notification.getId_notification());
        viewBinderHelper.setOpenOnlyOne(true);

        holder.content.setText(notification.getContent());
        holder.dateWrite.setText(notification.getDate_notification());

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemNotificationListener.deleteItemNotification(notification);
                notifications.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView content;
        private TextView dateWrite;

        private SwipeRevealLayout swipeRevealLayout;
        private TextView layoutDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            dateWrite = itemView.findViewById(R.id.dateWrite);

            layoutDelete = itemView.findViewById(R.id.layoutDelete);
            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
        }
    }
}
