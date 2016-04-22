package se.dropmedia.milan.assignment_5z;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Milan Gajic on 2016-04-21.
 */
public class QuoteAdapter extends BaseAdapter
{

    public ArrayList<Quotes> quotesList;
    Activity activity;

    public QuoteAdapter(Activity activity, ArrayList<Quotes> quotesList)
    {
        super();
        this.activity = activity;
        this.quotesList = quotesList;
    }

    @Override
    public int getCount() {
        return quotesList.size();
    }

    @Override
    public Object getItem(int position) {
        return quotesList.get(position);
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
            holder.image = (ImageView)convertView.findViewById(R.id.imageQuote);
            holder.tvQuotes = (TextView)convertView.findViewById(R.id.tvQuote);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        Quotes showQuotes = quotesList.get(position);

        holder.image.setImageResource(R.drawable.ic_quote);
        holder.tvQuotes.setText(showQuotes.getQuotes());


        return convertView;
    }
}
