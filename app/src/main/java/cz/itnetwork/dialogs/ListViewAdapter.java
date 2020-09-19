package cz.itnetwork.dialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<ListViewAdapterItem> {
    private final Context context;
    private final ArrayList<ListViewAdapterItem> items;

    public ListViewAdapter(Context context, ArrayList<ListViewAdapterItem> items) {
        super(context, -1, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.listview_adapter_item, parent, false);

        TextView itemName = itemView.findViewById(R.id.itemLabel);
        TextView itemDesc = itemView.findViewById(R.id.itemDesc);
        ImageView itemImage = itemView.findViewById(R.id.itemImg);

        ListViewAdapterItem item = items.get(position);

        itemName.setText(item.getName());
        itemDesc.setText(item.getDescription());

        if (item.isEnabled()) {
            itemImage.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_enabled));
        } else {
            itemImage.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_disabled));
        }

        if (position % 2 == 0) {
            itemView.setBackgroundColor(context.getResources().getColor(R.color.item_background_1));
        } else {
            itemView.setBackgroundColor(context.getResources().getColor(R.color.item_background_2));
        }

        return itemView;
    }
}
