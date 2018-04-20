package com.example.yui06.dictionary;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    EditText wordEditText;
    EditText meanEditText;
    EditText searchWordEditText;

    HashMap<String,String> hashMap;
    TreeSet wordSet;
    SharedPreferences pref;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        wordEditText = (EditText)findViewById(R.id.word);
        meanEditText = (EditText)findViewById(R.id.mean);
        searchWordEditText = (EditText)findViewById(R.id.searchWord);

        this.hashMap=new HashMap<>();
        wordSet = new TreeSet<String>();
        pref = getSharedPreferences("dictionary",MODE_PRIVATE);
        editor= pref.edit();

        editor.putString("technology","科学技術");
        editor.putString("develop","開発する" );
        editor.commit();

        wordSet.add("technology");
        wordSet.add("develop");

        for(String  word: wordSet){

            this.hashM
            ap.put(word,pref.getString(word,null));

            adapter.add("["+word+"]" + pref.getString(word,null));

        }


        listView.setAdapter(adapter);



    }

    public void add(View v){
        String entryWord = wordEditText.getText().toString();
        String entryMean = meanEditText.getText().toString();
        String entryObject = "["+entryWord+"]"+entryMean;

        wordSet.add(entryWord);
        editor.putString(entryWord,entryMean);
        editor.putStringSet("wordSet",wordSet);

        editor.commit();
        adapter.add(entryObject);
        wordSet.addAll(pref.getStringSet("wordSet",wordSet));




    }

    public void search(View v){
        String searchWord = searchWordEditText.getText().toString();
        wordSet.addAll(pref.getStringSet("wordSet",wordSet));


        for (String word: wordSet){
            hashMap.put(word,pref.getString(word,null));

        }

        if (hashMap.containsKey(searchWord)){
            Toast.makeText(this,hashMap.get(searchWord),Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"この単語は登録されてません",Toast.LENGTH_LONG).show();
        }
    }
}
