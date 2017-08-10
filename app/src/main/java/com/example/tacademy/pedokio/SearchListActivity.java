package com.example.tacademy.pedokio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Tacademy on 2017-08-07.
 */

public class SearchListActivity extends Activity {
    ListView listView;
    String[][] data = {
            {"광고1","내용1"},
            {"광고2","내용2"}
    };
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        listView = (ListView)findViewById(R.id.listview);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                startActivity(intent);
            }
        });


    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public String[] getItem(int i) {
            return data[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null) {
                view = SearchListActivity.this.getLayoutInflater().inflate(R.layout.cell_searchlist_layout, viewGroup, false);
            }
            TextView title = (TextView)view.findViewById(R.id.title);
            TextView content = (TextView)view.findViewById(R.id.content);
            final ImageView advi = (ImageView)view.findViewById(R.id.advi);

            String[] item = getItem(i);

            title.setText(item[1].trim());
            content.setText(item[0].trim());


            return view;
        }
    }
}

