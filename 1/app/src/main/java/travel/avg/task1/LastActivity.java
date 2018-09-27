package travel.avg.task1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import travel.avg.task1.Adapters.TwoAdapter;
import travel.avg.task1.DB.DBMethods;

public class LastActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button btn;
    ListView listView;
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
    Map<String, Integer> sortedMap = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout3);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view3);
        navigationView.setNavigationItemSelectedListener(this);

        listView = findViewById(R.id.listStatistic);
        btn = findViewById(R.id.again);

        Sort();

        for (Object name : sortedMap.keySet()) {
            list1.add(name.toString());
            list2.add(ArList.l.get(name));
        }

        TwoAdapter adapter = new TwoAdapter(this, list2, list1);
        listView.setAdapter(adapter);

        Date presentTime_Date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));

        ArList.dateList.put(dateFormat.format(presentTime_Date), sortedMap);
        DBMethods.saveMap(this, ArList.dateList);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArList.dateList.clear();

                ArList.list.clear();
                ArList.l.clear();
                ArList.count.clear();
                ArList.lda.clear();
                ArList.lst.clear();

                Intent intent = new Intent(LastActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Sort(){
        //convert map to a List
        List<Map.Entry<String, Integer>> list = new LinkedList<>(ArList.l.entrySet());

        //sorting the list with a comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        //convert sortedMap back to Map

        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout3);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.home:
                ArList.l.clear();
                ArList.count.clear();
                ArList.lst.clear();
                ArList.lda.clear();

                Intent intent = new Intent(LastActivity.this, MainActivity.class);
                startActivity(intent);
            break;
            case R.id.nav_send:

                Intent intent1 = new Intent(this, HistoryListActivity.class);
                startActivity(intent1);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout3);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



//        if (pSharedPref != null){
//            JSONObject jsonObject = new JSONObject(inputMap);
//            String jsonString = jsonObject.toString();
//            SharedPreferences.Editor editor = pSharedPref.edit();
//            editor.remove(key).commit();
//            editor.putString(key, jsonString);
//            editor.commit();
//        }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            return false;
        }
        else{
            return true;
        }
    }

}