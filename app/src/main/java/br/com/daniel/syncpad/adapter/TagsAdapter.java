package br.com.daniel.syncpad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView tagName = view.findViewById(R.id.item_tag_name);
        tagName.setText(tag.getName());

        String formatedDateTime = getFormatedDateTime(tag);
        TextView tagDate = view.findViewById(R.id.item_tag_last_update);
        tagDate.setText("Última atualização: " + formatedDateTime);

        return view;
    }

    private String getFormatedDateTime(Tag tag) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm Z");
        ZonedDateTime dateTime = ZonedDateTime.parse(tag.getDate(), formatter);

        DateTimeFormatter localFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZonedDateTime dateZoneTime = dateTime.withZoneSameInstant(ZoneId.systemDefault());

        return dateZoneTime.format(localFormatter);
    }
}
