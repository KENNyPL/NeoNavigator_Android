package pl.cydo.util;


import android.os.AsyncTask;
import pl.cydo.repository.CategoryRepository;

public class ApplicationResourceCollector extends AsyncTask<Object, Integer, Long>  {
    @Override
    protected Long doInBackground(Object... objects) {
        CategoryRepository.getINSTANCE();
        return 0L;
    }

    public ApplicationResourceCollector() {
        super();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(Long aLong) {
        super.onCancelled(aLong);
    }
}
