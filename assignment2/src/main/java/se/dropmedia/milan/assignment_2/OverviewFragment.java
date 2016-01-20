package se.dropmedia.milan.assignment_2;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment implements AdapterView.OnItemClickListener {

    View view;
    GridView myGrid;

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_overview, container, false);
        myGrid = (GridView)view.findViewById(R.id.gridView);
        myGrid.setAdapter(new GridAdapter(this.getActivity()));
        myGrid.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        ViewHolder holder = (ViewHolder)view.getTag();
        Movie movie = (Movie)holder.imgMovie.getTag();

        Bundle b = new Bundle();
        b.putString("name", movie.movieName);
        b.putString("year", movie.movieYear);
        b.putString("desc", movie.movieDescription);
        b.putInt("image", movie.imgDetail);

        Fragment frag = new DetailFragment();
        frag.setArguments(b);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, frag, "INPUT");
        ft.addToBackStack("INPUT");
        ft.commit();
    }

}

class ViewHolder
{
    ImageView imgMovie;
    TextView tvName;
    TextView tvYear;
    ViewHolder(View view)
    {
        imgMovie = (ImageView)view.findViewById(R.id.overview_image);
        tvName = (TextView)view.findViewById(R.id.overview_name);
        tvYear = (TextView)view.findViewById(R.id.overview_year);
    }
}

class GridAdapter extends BaseAdapter
{
    ArrayList<Movie> list;
    Context context;

    GridAdapter(Context context)
    {
        this.context = context;
        list = new ArrayList<Movie>();
        Resources res = context.getResources();

        PopulateList(res, list);
    }

    public void PopulateList(Resources res, ArrayList<Movie> list)
    {
        int counter = 1;
        while(counter < 11) {
            String[] movie = null;
            int[] year = null;
            TypedArray image = null;

            if(counter == 1) {
                movie = res.getStringArray(R.array.cops);
                year = res.getIntArray(R.array.cops);
                image = res.obtainTypedArray(R.array.cops);
            }
            if(counter == 2)
            {
                movie = res.getStringArray(R.array.eat_sleep_die);
                year = res.getIntArray(R.array.eat_sleep_die);
                image = res.obtainTypedArray(R.array.eat_sleep_die);
            }
            if(counter == 3)
            {
                movie = res.getStringArray(R.array.force_majeure);
                year = res.getIntArray(R.array.force_majeure);
                image = res.obtainTypedArray(R.array.force_majeure);
            }
            if(counter == 4)
            {
                movie = res.getStringArray(R.array.four_shades_of_brown);
                year = res.getIntArray(R.array.four_shades_of_brown);
                image = res.obtainTypedArray(R.array.four_shades_of_brown);
            }
            if(counter == 5)
            {
                movie = res.getStringArray(R.array.haxan);
                year = res.getIntArray(R.array.haxan);
                image = res.obtainTypedArray(R.array.haxan);
            }
            if(counter == 6)
            {
                movie = res.getStringArray(R.array.my_life_as_a_dog);
                year = res.getIntArray(R.array.my_life_as_a_dog);
                image = res.obtainTypedArray(R.array.my_life_as_a_dog);
            }
            if(counter == 7)
            {
                movie = res.getStringArray(R.array.persona);
                year = res.getIntArray(R.array.persona);
                image = res.obtainTypedArray(R.array.persona);
            }
            if(counter == 8)
            {
                movie = res.getStringArray(R.array.show_me_love);
                year = res.getIntArray(R.array.show_me_love);
                image = res.obtainTypedArray(R.array.show_me_love);
            }
            if(counter == 9)
            {
                movie = res.getStringArray(R.array.let_the_right_one_in);
                year = res.getIntArray(R.array.let_the_right_one_in);
                image = res.obtainTypedArray(R.array.let_the_right_one_in);
            }
            if(counter == 10)
            {
                movie = res.getStringArray(R.array.you_the_living);
                year = res.getIntArray(R.array.you_the_living);
                image = res.obtainTypedArray(R.array.you_the_living);
            }

            int imgDetail = image.getResourceId(3, 0);
            int imgOverview = image.getResourceId(4, 0);

            String name = null;
            int getYear = 0;
            String description = null;

            for (int i = 0; i < movie.length; i++) {
                if (i == 0) {
                    name = movie[i];
                }
                if (i == 2) {
                    description = movie[i];
                }
            }

            getYear = year[1];
            String stringYear = Integer.toString(getYear);

            Movie addToMovies = new Movie(name, stringYear, description, imgOverview, imgDetail);
            list.add(addToMovies);

            counter++;
        }
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.overview_list, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        Movie showMovie = list.get(position);
        holder.imgMovie.setImageResource(showMovie.imgOverview);

        holder.tvName.setText(showMovie.movieName);
        holder.tvYear.setText(showMovie.movieYear);
        holder.imgMovie.setTag(showMovie);

        return row;
    }
}