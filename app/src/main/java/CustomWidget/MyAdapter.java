package CustomWidget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wyh.customwidget.R;

import java.util.List;

/**
 * Created by wyh on 15/10/6.
 */
public class MyAdapter extends BaseAdapter {

    private List<String> list;
    private LayoutInflater inflater;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
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

        convertView = inflater.inflate(R.layout.item,null);

        return convertView;
    }

    class ViewHolder
    {
        ImageView imageView;
        TextView textView;
    }
}
