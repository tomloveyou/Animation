package personcom.yl.animotiontest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by changlianjiuzhou on 17/2/20.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.TestViewHolder> implements View.OnTouchListener, View.OnLongClickListener {

    private List<String> data;
    private ArrayList<Integer> colors = new ArrayList<>();

    public MainAdapter(List<String> data) {
        this.data = data;
        colors.addAll(ColorsHelper.COLORS);
    }

    private OnClickListener listener;


    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, final int position) {
        holder.testName.setText(data.get(position));
        // holder.itemView.setOnTouchListener(this);
        holder.itemView.setOnLongClickListener(this);
        holder.itemView.setBackgroundColor(colors.get(position));
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClikListener(position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.v("ada", "离开");
        }
        return true;
    }

    public void removeItemAtPosition(int position) {
        colors.remove(position);
        notifyItemRemoved(position);
    }

    public void addItemAtPosition(int position) {
        colors.add(position, ColorsHelper.COLORS.get(position));
        notifyItemInserted(position);
    }

    public void changeItemAtPosition(int position) {
        colors.set(position, ColorsHelper.getRandomColor());
        notifyItemChanged(position);
    }

    @Override
    public boolean onLongClick(View v) {
        Log.v("ada", "长按");
        return false;
    }

    class TestViewHolder extends RecyclerView.ViewHolder {
        protected TextView testName;

        public TestViewHolder(View itemView) {
            super(itemView);
            testName = (TextView) itemView.findViewById(R.id.test_name);
        }
    }

    interface OnClickListener {
        void OnClikListener(int position);
    }
}
