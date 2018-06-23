package br.com.daniel.syncpad.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.daniel.syncpad.R;
import br.com.daniel.syncpad.adapter.listener.OnItemClickListener;
import br.com.daniel.syncpad.model.Tag;

public class TagsAdapterRecycler extends RecyclerView.Adapter<TagsAdapterRecycler.TagViewHolder> {

    private List<Tag> tags;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public TagsAdapterRecycler(Context context, List<Tag> tags){
        this.context = context;
        this.tags = tags;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TagsAdapterRecycler.TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TagsAdapterRecycler.TagViewHolder holder, int position) {
        Tag tag = tags.get(position);
        holder.complete(tag);
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    class TagViewHolder extends RecyclerView.ViewHolder{

        private final TextView tagName;
        private final TextView tagLastUpdated;
        private Tag tag;

        public TagViewHolder(View itemView) {
            super(itemView);
            tagName = itemView.findViewById(R.id.item_tag_name);
            tagLastUpdated = itemView.findViewById(R.id.item_tag_last_update);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(tag);
                }
            });
        }

        public void complete(Tag tag){
            this.tag = tag;

            tagName.setText(tag.getName());
            String formatedDateTime = getFormatedDateTime(tag);
            tagLastUpdated.setText(context.getString(R.string.last_update) + ": " + formatedDateTime);
        }
    }

    private String getFormatedDateTime(Tag tag) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm Z");
        ZonedDateTime dateTime = ZonedDateTime.parse(tag.getDate(), formatter);

        DateTimeFormatter localFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZonedDateTime dateZoneTime = dateTime.withZoneSameInstant(ZoneId.systemDefault());

        return dateZoneTime.format(localFormatter);
    }
}
