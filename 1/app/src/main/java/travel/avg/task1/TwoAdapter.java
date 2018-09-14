package travel.avg.task1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoAdapter extends ArrayAdapter<Integer> {
    Context context;
    List<String> values;
    ArrayList<Integer> count;
    int max;

    public TwoAdapter(Context context, ArrayList<Integer> count, ArrayList<String> values) {
        super(context, R.layout.layout_adapter2, count);

        this.context = context;
        this.count = count;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_adapter2, parent, false);
        }

        TextView nameList = view.findViewById(R.id.nameList);
        TextView countList = view.findViewById(R.id.countList);
        ProgressBar progressBar = view.findViewById(R.id.progress);

        countList.setText(count.get(position).toString());
        nameList.setText(values.get(position).toString());
        progressBar.setMax(Collections.max(count));
        progressBar.setProgress(count.get(position));

        return view;
    }
}
