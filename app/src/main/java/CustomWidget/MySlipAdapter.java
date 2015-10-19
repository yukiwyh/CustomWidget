package CustomWidget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.wyh.customwidget.R;

import java.util.List;

/**
 * Created by wyh on 15/10/18.
 */
public class MySlipAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;
    private LayoutInflater inflater;

    public MySlipAdapter(List<String> list,Context context)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View contentView = inflater.inflate(R.layout.item_content,null);
        View menuView = inflater.inflate(R.layout.item_menu,null);
        convertView = new ItemView(contentView,menuView);
        return convertView;

    }
}
