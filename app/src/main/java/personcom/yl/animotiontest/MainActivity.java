package personcom.yl.animotiontest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnClickListener {

    protected RecyclerView testRecycleview;
    protected FloatingActionButton testButton;
    protected RelativeLayout activityMain;
    private MainAdapter adapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        adapter = new MainAdapter(data);
        testRecycleview = (RecyclerView) findViewById(R.id.test_recycleview);
        testButton = (FloatingActionButton) findViewById(R.id.test_button);
        activityMain = (RelativeLayout) findViewById(R.id.activity_main);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        testRecycleview.setLayoutManager(manager);
        testRecycleview.setAdapter(adapter);
        for (int i = 0; i < 50; i++) {
            data.add("2342");
        }
        adapter.notifyDataSetChanged();
       setupRecyclerViewAnimator();
        adapter.setListener(this);

    }
    private void setupRecyclerViewAnimator() {

            testRecycleview.setItemAnimator(new MyItemAnimator());

    }

    @Override
    public void OnClikListener(int position) {
        adapter.changeItemAtPosition(position);

    }
}
