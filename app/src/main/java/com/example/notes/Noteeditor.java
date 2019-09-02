package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class Noteeditor extends AppCompatActivity {
    private EditText Edittext;
    private int noteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteeditor);

        Edittext=(EditText)findViewById(R.id.eteditnote);

        Intent intent=getIntent();
        noteid=intent.getIntExtra("noteId", -1);

        if(noteid!=-1){
            Edittext.setText(SecondActivity.notes.get(noteid));
        }
        else{
            SecondActivity.notes.add("");
            noteid=SecondActivity.notes.size()-1;
            SecondActivity.arrayAdapter.notifyDataSetChanged();
        }

        Edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SecondActivity.notes.set(noteid,String.valueOf(charSequence));
                SecondActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(SecondActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
