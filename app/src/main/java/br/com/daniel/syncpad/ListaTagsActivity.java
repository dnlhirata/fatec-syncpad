package br.com.daniel.syncpad;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.daniel.syncpad.model.Tag;

public class ListaTagsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tags);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();

        tx.replace(R.id.frame_principal, new ListaTagsFragment());
        if (isLandscape()) {
            tx.replace(R.id.frame_secundario, new TagContentFragment());
        }
        tx.commit();

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_tag, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_map:
                Toast.makeText(this, "Oi", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }*/

    private boolean isLandscape() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }

    public void selectTag(Tag tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!isLandscape()) {
            FragmentTransaction tx = fragmentManager.beginTransaction();

            TagContentFragment tagContentFragment = new TagContentFragment();
            Bundle params = new Bundle();
            params.putSerializable("tag", tag);
            tagContentFragment.setArguments(params);

            tx.replace(R.id.frame_principal, tagContentFragment);
            tx.addToBackStack(null);

            tx.commit();
        } else {
            TagContentFragment contentFragment = (TagContentFragment) fragmentManager.findFragmentById(R.id.frame_secundario);
            contentFragment.populateWith(tag);
        }
    }
}
