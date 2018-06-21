package br.com.daniel.syncpad;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.daniel.syncpad.model.Tag;


/**
 * A simple {@link Fragment} subclass.
 */
public class TagContentFragment extends Fragment {

    private TextView tagName;
    private TextView tagContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tag_content, container, false);

        tagName = view.findViewById(R.id.tag_name);
        tagContent = view.findViewById(R.id.tag_content);

        Bundle params = getArguments();
        if (params != null){
            Tag tag = (Tag) params.getSerializable("tag");
            populateWith(tag);
        }

        return view;
    }

    public void populateWith(Tag tag){
        tagName.setText(tag.getName());
        tagContent.setText(tag.getContent());
    }

}
