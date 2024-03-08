package com.example.qrelcome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdminUserArrayAdapter extends ArrayAdapter<UserProfile> {

    public AdminUserArrayAdapter(Context context, ArrayList<UserProfile> data) {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if  (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.user_adapter_content, parent, false);
        } else {
            view = convertView;
        }

        UserProfile user = getItem(position);
        TextView userName = view.findViewById(R.id.admin_profile_browse_user_name);

        try {
            userName.setText(user.getName());
        } catch (NullPointerException e) {
            userName.setText("User Not Found"); // TODO: should this be extracted as string resource?
        }

        return view;
    }
}
