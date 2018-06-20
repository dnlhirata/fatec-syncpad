package br.com.daniel.syncpad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.daniel.syncpad.R;
import br.com.daniel.syncpad.model.Tag;

public class TagsAdapter extends BaseAdapter {

    private final List<Tag> tags;
    private final Context context;

    public TagsAdapter(Context context, List<Tag> tags) {
        this.context = context;
        this.tags = tags;
    }

    @Override
    public int getCount() {
        return tags.size();
    }

    @Override
    public Object getItem(int position) {
        return tags.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tag tag = tags.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);

        TextView tagName = view.findViewById(R.id.item_tag_name);
        tagName.setText(tag.getName());
        TextView tagDate = view.findViewById(R.id.item_tag_last_update);
        tagDate.setText(tag.getDate());

        return view;
    }
}
