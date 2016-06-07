package se.dropmedia.milan.assignment_3;


import android.app.Fragment;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {

    View view;
    ArrayList<Quotes> listQuotes = new ArrayList<Quotes>();
    ListViewAdapter adapter;
    //ImageButton button;

    ProgressBar pb;

    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_overview, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        pb = (ProgressBar)view.findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        //PopulateList();
        GetValues();

        adapter = new ListViewAdapter(this.getActivity(), listQuotes);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "Floating Button Works!", Toast.LENGTH_SHORT).show();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                GetValues();
                //if (isOnline())
                //{
                //    RequestData("https://api.github.com/zen?access_token=dd5a2113a9ece3703ee99e258891da11c519c8f3");
                //} else
                //{
                //    Toast.makeText(getActivity(), "NO NETWORK!", Toast.LENGTH_SHORT).show();
                //}


            }
        });

        return view;
    }

    private void RequestData(String uri)
    {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(this.getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void GetValues()
    {
        if (isOnline())
        {
            RequestData("https://api.github.com/zen?access_token=dd5a2113a9ece3703ee99e258891da11c519c8f3");
        } else
        {
            Toast.makeText(getActivity(), "NO NETWORK!", Toast.LENGTH_SHORT).show();
        }
    }

    private void PopulateList()
    {
        //String newQuotes = "";

        //Quotes addToQuotes = new Quotes(newQuotes);
        //listQuotes.add(addToQuotes);

        //GetValues();

    }

    private void PopulateListFromAPi(String message)
    {
        String newQuotes = message;

        Quotes addToQuotes = new Quotes(newQuotes);
        listQuotes.add(addToQuotes);

        adapter.notifyDataSetChanged();

    }

    private class MyTask extends AsyncTask<String, String, String>
    {

        @Override
        protected void onPreExecute()
        {
            //Toast.makeText(getActivity(), "Starting Task", Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String content = "";
            try {
                content = HttpManager.getData(params[0]);
                //Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        @Override
        protected void onPostExecute(String result)
        {
            //Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

            PopulateListFromAPi(result);
            pb.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            //Toast.makeText(getActivity(), values[0], Toast.LENGTH_SHORT).show();
        }

    }

}
