package com.sunita.unittestingframework.framework;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;

import com.sunita.unittestingframework.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    private List<String> data = new ArrayList<>();
    private int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVFileData csvFileData = new CSVFileData(inputStream);
        List<String[]> scoreList = csvFileData.read();

        for (String[] scoreData : scoreList) {
            data.addAll(Arrays.asList(scoreData));
            itemArrayAdapter.add(data.get(i));
            i++;
        }
    }
}