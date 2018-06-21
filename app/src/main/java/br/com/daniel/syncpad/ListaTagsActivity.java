package br.com.daniel.syncpad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import br.com.daniel.syncpad.adapter.TagsAdapter;
import br.com.daniel.syncpad.firebase.FirebaseHelper;
import br.com.daniel.syncpad.model.Tag;

public class ListaTagsActivity extends AppCompatActivity {

    private ArrayList<Tag> tags;
    private TagsAdapter tagsAdapter;
    private DatabaseReference firebaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tags);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseHelper fbHelper = new FirebaseHelper();
        firebaseReference = fbHelper.configuraFirebase();

        ListView listaTags = findViewById(R.id.tag_list);
        tags = new ArrayList<Tag>();
        tagsAdapter = new TagsAdapter(this, tags);
        listaTags.setAdapter(tagsAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Inicia TagActivity e "joga" a lista de tags
                Intent telaTag = new Intent(ListaTagsActivity.this, TagActivity.class);
                telaTag.putExtra("tags", tags);
                startActivity(telaTag);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Recupera as tags salvas no Firebase
                tags.clear();
                for (DataSnapshot json : dataSnapshot.getChildren()) {
                    Tag tag = json.getValue(Tag.class);
                    tag.setId(json.getKey());
                    tags.add(tag);
                }

                tagsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
