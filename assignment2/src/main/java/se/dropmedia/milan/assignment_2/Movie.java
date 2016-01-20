package se.dropmedia.milan.assignment_2;

/**
 * Created by MAH on 2015-09-27.
 */
public class Movie
{
    /*
    public static final String MOVIE_NAME = "movieName";
    public static final String MOVIE_YEAR = "movieYear";
    public static final String MOVIE_DESC = "movieDescription";
    public static final String IMG_OVERVIEW = "imageOverview";
    public static final String IMG_DETAILVIEW = "imageDetailview";
    */

    String movieName;
    String movieYear;
    String movieDescription;
    int imgOverview;
    int imgDetail;

    Movie(String movieName, String movieYear, String movieDescription, int imgOverview, int imgDetail)
    {
        this.movieName = movieName;
        this.movieYear = movieYear;
        this.movieDescription = movieDescription;
        this.imgOverview = imgOverview;
        this.imgDetail = imgDetail;
    }

    /*
    Movie(Bundle b) {
        if(b != null)
        {
            this.movieName = b.getString(MOVIE_NAME);
            this.movieYear = b.getString(MOVIE_YEAR);
            this.movieDescription = b.getString(MOVIE_DESC);
            this.imgOverview = b.getInt(IMG_OVERVIEW);
            this.imgDetail = b.getInt(IMG_DETAILVIEW);
        }
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(MOVIE_NAME, this.movieName);
        b.putString(MOVIE_YEAR, this.movieYear);
        b.putString(MOVIE_DESC, this.movieDescription);
        b.putInt(IMG_OVERVIEW, this.imgOverview);
        b.putInt(IMG_DETAILVIEW, this.imgDetail);
        return b;
    }

    @Override
    public String toString() {
        return movieName;
    } */
}
