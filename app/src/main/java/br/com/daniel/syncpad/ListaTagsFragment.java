package br.com.daniel.syncpad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.daniel.syncpad.adapter.TagsAdapter;
import br.com.daniel.syncpad.firebase.FirebaseHelper;
import br.com.daniel.syncpad.model.Tag;

public class ListaTagsFragment extends Fragment {

    private ArrayList<Tag> tags;
    private TagsAdapter tagsAdapter;
    private DatabaseReference firebaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_tags, container, false);

        FirebaseHelper fbHelper = new FirebaseHelper();
        firebaseReference = fbHelper.configuraFirebase();

        ListView listaTags = view.findViewById(R.id.tag_list);
        tags = new ArrayList<Tag>();
        tagsAdapter = new TagsAdapter(getContext(), tags);
        listaTags.setAdapter(tagsAdapter);

        listaTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tag tag = (Tag) parent.getItemAtPosition(position);

                ListaTagsActivity listaTagsActivity = (ListaTagsActivity) getActivity();
                listaTagsActivity.selectTag(tag);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Inicia TagActivity e "joga" a lista de tags
                Intent telaTag = new Intent(getContext(), TagActivity.class);
                telaTag.putExtra("tags", tags);
                startActivity(telaTag);
            }
        });

        return view;
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
                sortByDate(tags);
                tagsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void sortByDate(ArrayList<Tag> tags) {
        Collections.sort(tags, new Comparator<Tag>() {
            @Override
            public int compare(Tag tag1, Tag tag2) {
                return tag1.getDate().compareTo(tag2.getDate());
            }
        });
    }
}
