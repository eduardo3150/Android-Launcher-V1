package com.chavez.eduardo.testlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Eduardo_Chavez on 30/3/2017.
 */

public class LauncherItemAdapter extends RecyclerView.Adapter<LauncherItemAdapter.LauncherViewHolder> {
    ArrayList<AppDetail> appDetails = new ArrayList<>();
    Context context;
    PackageManager manager;


    public LauncherItemAdapter(ArrayList<AppDetail> appDetails, Context context, PackageManager manager) {
        this.appDetails = appDetails;
        this.context = context;
        this.manager = manager;
    }

    @Override
    public LauncherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);
        return new LauncherViewHolder(row);
    }

    @Override
    public void onBindViewHolder(LauncherViewHolder holder, int position) {
        final AppDetail detail = appDetails.get(position);
        holder.appLogo.setImageDrawable(detail.getIcon());
        //holder.appName.setText(detail.getName());
        holder.appPackage.setText(detail.getLabel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = manager.getLaunchIntentForPackage(detail.getName().toString());
               context.startActivity(i);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Uri packageUri = Uri.parse("package:"+detail.getName().toString());
                Intent intent = new Intent(Intent.ACTION_DELETE,packageUri);
                context.startActivity(intent);
                //Toast.makeText(context,"Seleccionaste "+ detail.getLabel().toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return appDetails.size();
    }

    public class LauncherViewHolder extends RecyclerView.ViewHolder {
        private ImageView appLogo;
        private TextView appName;
        private TextView appPackage;

        public LauncherViewHolder(View itemView) {
            super(itemView);
            appLogo = (ImageView) itemView.findViewById(R.id.item_app_icon);
            appName = (TextView) itemView.findViewById(R.id.item_app_name);
            appPackage = (TextView) itemView.findViewById(R.id.item_app_label);
        }

        public ImageView getAppLogo() {
            return appLogo;
        }

        public void setAppLogo(ImageView appLogo) {
            this.appLogo = appLogo;
        }

        public TextView getAppName() {
            return appName;
        }

        public void setAppName(TextView appName) {
            this.appName = appName;
        }

        public TextView getAppPackage() {
            return appPackage;
        }

        public void setAppPackage(TextView appPackage) {
            this.appPackage = appPackage;
        }
    }
}
