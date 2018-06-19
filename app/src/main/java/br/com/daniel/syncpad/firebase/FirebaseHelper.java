package br.com.daniel.syncpad.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.daniel.syncpad.model.Tag;

public class FirebaseHelper {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference tagsReference;

    public DatabaseReference configuraFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        tagsReference = firebaseDatabase.getReference("tags");
        return tagsReference;
    }

}
