package se.dropmedia.milan.assignment_3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MAH on 2015-10-17.
 */
public class ListViewAdapter extends BaseAdapter {

    public ArrayList<Quotes> listQuotes;
    Activity activity;

    public ListViewAdapter(Activity activity, ArrayList<Quotes> listQuotes)
    {
        super();
        this.activity = activity;
        this.listQuotes = listQuotes;
    }

    @Override
    public int getCount() {
        return listQuotes.size();
    }

    @Override
    public Object getItem(int position) {
        return listQuotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder
    {
        ImageView image;
        TextView tvQuotes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_quotes, null);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(R.id.image_quote);
            holder.tvQuotes = (TextView)convertView.findViewById(R.id.tv_quote);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        Quotes showQuotes = listQuotes.get(position);

        holder.image.setImageResource(R.drawable.ic_quote);
        holder.tvQuotes.setText(showQuotes.GetQuotes());


        return convertView;
    }
}
