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

import java.util.Collections;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.model.entites.Topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MultiCheckboxSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private final Context context;
    private ArrayList<Topic> subjects;
    private ArrayList<Topic> selectedSubjects;
    private LayoutInflater inflater;
//    private List<Boolean> selectedList;
    private List<Integer> selectedPositions = new ArrayList<>();

    public MultiCheckboxSpinnerAdapter(Context context, ArrayList<Topic> subjects) {
        this.context = context;
        this.subjects = subjects;
        this.selectedSubjects = new ArrayList<>();
        inflater = LayoutInflater.from(context);

    }
    public void printSelectedItems() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selectedPositions.size(); i++) {
            int position = selectedPositions.get(i);
            sb.append(subjects.get(position).getName());
            if (i != selectedPositions.size() - 1) {
                sb.append(", ");
            }
        }
        Log.d("Selected items", sb.toString());
    }


    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int i) {
        return subjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.spinner_item_checkbox, null);
            viewHolder = new ViewHolder();
            viewHolder.subjectName = view.findViewById(R.id.textview);
            viewHolder.checkBox = view.findViewById(R.id.checkbox);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.checkBox.setChecked(selectedPositions.contains(i)); // kiểm tra nếu position được chọn thì set giá trị checked cho checkbox

        viewHolder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedPositions.add(i); // thêm position được chọn vào danh sách
            } else {
                selectedPositions.remove(Integer.valueOf(i)); // xoá position được bỏ chọn khỏi danh sách
            }
        });


        viewHolder.subjectName.setText(subjects.get(i).getName());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.spinner_item_checkbox, null);
        }

        TextView subjectName = view.findViewById(R.id.textview);
        CheckBox checkBox = view.findViewById(R.id.checkbox);

        Topic subject = subjects.get(position);

        subjectName.setText(subject.getName());
        //checkBox.setChecked(subject.isSelected());

//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean isChecked = ((CheckBox) view).isChecked();
//                subject.setSelected(isChecked);
//                if (isChecked) {
//                    selectedSubjects.add(subject);
//                } else {
//                    selectedSubjects.remove(subject);
//                }
//            }
//        });

        return view;
    }

    public ArrayList<Topic> getSelectedSubjects() {
        return selectedSubjects;
    }

    private static class ViewHolder {
        TextView subjectName;
        CheckBox checkBox;
    }
}
