package se.mah.tsroax.assignment_1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowQuote extends Fragment {

    private View view;
    private TextView tvShowQuote;
    private TextView tvShowDate;
    private String[] quotes = new String[6];

    public ShowQuote() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_show_quote, container, false);

        InitializeComponents();
        ShowNewQuote();
        return view;
    }

    private void InitializeComponents()
    {
        tvShowQuote = (TextView)view.findViewById(R.id.tv_show_quotes);
        tvShowDate = (TextView)view.findViewById(R.id.tv_show_date);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fd = df.format(c.getTime());
        tvShowDate.setText(fd);

        quotes[0] = new String("Strive not to be a success, but rather to be of value.");
        quotes[1] = new String("You miss 100% of the shots you don’t take.");
        quotes[2] = new String("Every strike brings me closer to the next home run.");
        quotes[3] = new String("Don't cry because it's over, smile because it happened.");
        quotes[4] = new String("Be yourself; everyone else is already taken.");
        quotes[5] = new String("A room without books is like a body without a soul.");
    }

    public void ShowNewQuote()
    {
        Random rnd = new Random();
        int index = rnd.nextInt(6);
        tvShowQuote.setText(quotes[index]);
    }
}
