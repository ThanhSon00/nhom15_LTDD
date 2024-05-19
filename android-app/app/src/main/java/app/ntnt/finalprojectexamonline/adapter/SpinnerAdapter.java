package app.ntnt.finalprojectexamonline.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.model.entites.Topic;

public class SpinnerAdapter extends ArrayAdapter<Topic> {

    private List<Topic> topics;
    private LayoutInflater inflater;
    private List<Integer> positions = new ArrayList<>();

    private boolean[] selected;
    private List<Topic> selectedItems;

    public SpinnerAdapter(Context context, List<Topic> topics, List<Topic> selectedItems, boolean[] selected) {
        super(context, 0, topics);
        this.topics = topics;
        this.selected = selected;
        this.selectedItems = selectedItems;
        notifyDataSetChanged();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = inflater.inflate(R.layout.spinner_item_checkbox, parent, false);
//        TextView textView = view.findViewById(R.id.textview);
//        textView.setText(selectedItems.toString());

        View view = inflater.inflate(R.layout.simple_text, parent, false);
        TextView textView = view.findViewById(R.id.tv_simple_text);
        List<Long> items = new ArrayList<>();
        for(Topic topic:selectedItems)
        {
            items.add(topic.getId());
        }
        textView.setText("Đang chọn"+items.toString());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.spinner_item_checkbox, parent, false);
        CheckBox checkbox = view.findViewById(R.id.checkbox);
        TextView textView = view.findViewById(R.id.textview);
        Topic item = getItem(position);
        textView.setText(item.getName());
        checkbox.setChecked(selected[position]);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selected[position] = isChecked;
                if (isChecked) {
                    selectedItems.add(item);
                } else {
                    selectedItems.remove(item);
                }

            }
        });


        return view;
    }


}

