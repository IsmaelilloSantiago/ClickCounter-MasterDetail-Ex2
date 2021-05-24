package es.ulpgc.eite.cleancode.clickcounter.master;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.cleancode.clickcounter.R;
import es.ulpgc.eite.cleancode.clickcounter.app.AppMediator;
import es.ulpgc.eite.cleancode.clickcounter.data.CounterData;
import es.ulpgc.eite.cleancode.clickcounter.detail.DetailActivity;

public class MasterActivity
    extends AppCompatActivity implements MasterContract.View {

  public static String TAG = MasterActivity.class.getSimpleName();

  private MasterContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_master);
    getSupportActionBar().setTitle(R.string.app_name);

    if (savedInstanceState == null) {
      AppMediator.resetInstance();
    }

    // do the setup
    MasterScreen.configure(this);

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
  public void onDataUpdated(MasterViewModel viewModel) {
    //Log.e(TAG, "onDataUpdated()");

    // deal with the datasource
    ((ListView) findViewById(R.id.list)).setAdapter(new MasterAdapter(
            this, viewModel.datasource, new View.OnClickListener() {

          @Override
          public void onClick(View view) {
            CounterData data = (CounterData) view.getTag();
            presenter.selectItem(data);

          }
        })
    );
  }

  @Override
  public void navigateToNextScreen() {
    Intent intent = new Intent(this, DetailActivity.class);
    startActivity(intent);
  }


  @Override
  public void injectPresenter(MasterContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
