package br.com.daniel.syncpad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.daniel.syncpad.firebase.FirebaseHelper;
import br.com.daniel.syncpad.model.Tag;

public class TagActivity extends AppCompatActivity {
    private DatabaseReference firebaseReference;
    private EditText tagName;
    private EditText tagContent;
    private String tagId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        FirebaseHelper fbHelper = new FirebaseHelper();
        firebaseReference = fbHelper.configuraFirebase();

        tagName = findViewById(R.id.tag_name);
        tagContent = findViewById(R.id.tag_content);

        final EditText tagName = findViewById(R.id.tag_name);
        Intent intent = getIntent();
        final ArrayList<Tag> tags = (ArrayList) intent.getSerializableExtra("tags");

        tagName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    for (Tag tag : tags) {
                        if (tag.getName().equals(tagName.getEditableText().toString())) {
                            tagId = tag.getId();
                            tagContent.setText(tag.getContent());
                        } else {
                            tagId = null;
                            tagContent.setText("");
                        }
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_tags, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_tag:
                if (tagId == null) {
                    String key = firebaseReference.push().getKey();
                    salvaTag(key);
                } else {
                    salvaTag(tagId);
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void salvaTag(String key) {
        String name = tagName.getEditableText().toString();
        String content = tagContent.getEditableText().toString();
        Tag tag = new Tag(key, name, content);
        firebaseReference.child(key).setValue(tag);
    }

}
