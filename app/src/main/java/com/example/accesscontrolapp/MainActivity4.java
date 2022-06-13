package com.example.accesscontrolapp;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    ListView listView;
    int count=0;
    int siz=0;
    public static int i;
     DBHandler dbHandler;
    public static String appNames;
MainActivity4 mainActivity4;
    String ch[][]=new String[50][50];
    ArrayAdapter<Applist> adapter;

    List<Applist> list=new ArrayList<Applist>();
    ArrayList<Applist> listItems=new ArrayList<Applist>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicationlistid);
//Intent intent=new Intent(this,MainActivity4.class);
//startActivity(intent);

        listView=(ListView) findViewById(R.id.applicationList);


        List<PackageInfo> packageList = getPackageManager().getInstalledPackages(0);
        List<Applist> apps = new ArrayList<Applist>();
        for (int i = 0; i <  ((List<?>) packageList).size(); i++) {
            PackageInfo packageInfo = packageList.get(i);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = (packageInfo.applicationInfo.loadLabel(getPackageManager())).toString();
                if(appName!=null)
                Log.e("App Name:" + Integer.toString(i), String.valueOf(appName));
               // new Applist(appName);
                apps.add(new Applist(appName));


                adapter=new ArrayAdapter<Applist>(this, android.R.layout.simple_list_item_1,apps);
                listView.setAdapter(adapter);
                listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE_MODAL);
              //  i=listItems.size();
                listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                    @Override
                    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long l, boolean b) {
                        count=count+1;

                        actionMode.setTitle(count+"items selected");
                        listItems.add(apps.get(position));
                    }

                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        MenuInflater inflater=actionMode.getMenuInflater();
                        inflater.inflate(R.menu.context_menu, menu);
                        return true
                                ;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.select_id:
                                for (Applist msg : listItems)
                                {//adapter.remove(msg);
                                    count = 0;
                                    //actionMode.finish();

                                    // creating a new dbhandler class
                                    // and passing our context to it.
                                    dbHandler = new DBHandler(MainActivity4.this);

                                      appNames= msg.getName();
                                    Log.e(String.valueOf(this), String.valueOf(appNames));
                                    // on below line we are calling a method to add new
                                    // course to sqlite data and pass all our values to it.
                                    dbHandler.addNewItem(msg.getName(),MainActivity5.dataTime);




                                    //return true;}
                                }
                                Intent intent=new Intent(MainActivity4.this,MainActivity3.class);
startActivity(intent);
                            default:
                                return false;
                        }}

                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {

                    }
                });}




        }

    }


    public class Applist
    {String name;
        public Applist(String name)
        {this.name=name;}

        public String getName() {
            return name;
        }
    }
}