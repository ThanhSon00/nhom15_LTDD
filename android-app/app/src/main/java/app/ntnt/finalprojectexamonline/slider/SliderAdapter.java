package app.ntnt.finalprojectexamonline.slider;

import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import app.ntnt.finalprojectexamonline.R;


public class SliderAdapter extends  Adapter<SliderAdapter.SliderViewHolder> {
    private Context context;
    List<SliderItem> sliderItems;

    public SliderAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<SliderItem> sliderItems)
    {
        this.sliderItems = sliderItems;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject,parent,false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider_home,parent,false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        SliderItem sliderItem = sliderItems.get(position);
        if(sliderItem==null)
            return;
        holder.roundedImageView.setImageResource(sliderItem.getImage());

    }




    @Override
    public int getItemCount() {
        if(sliderItems!=null)
            return sliderItems.size();
        return 0;
    }

    public class SliderViewHolder extends ViewHolder {
        private RoundedImageView roundedImageView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            roundedImageView = itemView.findViewById(R.id.item_slider);
        }
    }

}
