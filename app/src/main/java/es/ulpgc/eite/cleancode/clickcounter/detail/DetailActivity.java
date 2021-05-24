package es.ulpgc.eite.cleancode.clickcounter.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.cleancode.clickcounter.R;

public class DetailActivity
    extends AppCompatActivity implements DetailContract.View {

  public static String TAG = DetailActivity.class.getSimpleName();

  private DetailContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    getSupportActionBar().setTitle(R.string.app_name);

    // do the setup
    DetailScreen.configure(this);

    if (savedInstanceState == null) {
      presenter.onStart();

    } else {
      presenter.onRestart();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // load the data
    presenter.onResume();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    presenter.onBackPressed();
  }

  @Override
  protected void onPause() {
    super.onPause();

    presenter.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    presenter.onDestroy();
  }


  public void onButtonPressed(View view) {
    presenter.onButtonPressed();
  }


  @Override
  public void onDataUpdated(DetailViewModel viewModel) {
    //Log.e(TAG, "onDataUpdated()");

    // deal with the data
    ((TextView) findViewById(R.id.counter)).setText(viewModel.data);
    ((TextView) findViewById(R.id.clicks)).setText(viewModel.clicks);
  }

  @Override
  public void injectPresenter(DetailContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
