package com.chavez.eduardo.testlauncher;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PackageManager manager;
    ArrayList<AppDetail> apps = new ArrayList<>();
    List<ResolveInfo> availableActivities;
    int BANDERA = 0;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();
        LinearLayout ll = (LinearLayout) findViewById(R.id.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ll.setBackground(wallpaperDrawable);
        }

        gridLayoutManager = new GridLayoutManager(MainActivity.this,3);

        loadApps();
        loadListView();

    }

    private void loadApps(){
        manager = getPackageManager();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            AppDetail app = new AppDetail(ri.loadLabel(manager),ri.activityInfo.packageName,ri.activityInfo.loadIcon(manager));

            /**
            if ((app.name.equals("com.android.calculator2"))|| (app.name.equals("com.android.chrome"))) {
                BANDERA = 10;
            } else {
                BANDERA = 5;
            }

            if (BANDERA==10){
                apps.add(app);
            }
            Log.d("Paquetes:", "Paquete "+app.getName().toString());
            **/
            apps.add(app);
        }
    }


    private void loadListView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new LauncherItemAdapter(apps,this,manager));
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        availableActivities.clear();
        apps.clear();
        loadApps();
        loadListView();
    }

    @Override
    public void onBackPressed() {
    }
}
