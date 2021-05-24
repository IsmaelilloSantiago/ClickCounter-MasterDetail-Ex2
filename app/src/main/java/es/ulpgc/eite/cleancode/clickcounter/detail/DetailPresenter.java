package es.ulpgc.eite.cleancode.clickcounter.detail;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.clickcounter.app.AppMediator;
import es.ulpgc.eite.cleancode.clickcounter.app.DetailToMasterState;
import es.ulpgc.eite.cleancode.clickcounter.app.MasterToDetailState;

public class DetailPresenter implements DetailContract.Presenter {

  public static String TAG = DetailPresenter.class.getSimpleName();

  private WeakReference<DetailContract.View> view;
  private DetailState state;
  private DetailContract.Model model;
  private AppMediator mediator;

  public DetailPresenter(AppMediator mediator) {
    this.mediator = mediator;
    state = mediator.getDetailState();
  }


  @Override
  public void onStart() {
    // Log.e(TAG, "onStart()");

    // initialize the state if is necessary
    if (state == null) {
      state = new DetailState();
    }

    //model.inicializar();

    // use passed state if is necessary
    MasterToDetailState savedState = getStateFromPreviousScreen();
    Log.e(TAG,savedState.clicks);
    if (savedState != null) {

      // update the model if is necessary
      state.clicks = savedState.clicks;
      model.onDataFromPreviousScreen(savedState.data);
    }
  }

  @Override
  public void onRestart() {
    // Log.e(TAG, "onRestart()");

    // update the model if is necessary
    model.onRestartScreen(state.data);
  }

  @Override
  public void onResume() {
    // Log.e(TAG, "onResume()");

    // call the model and update the state
    state.data = model.getStoredData();

    // update the view
    view.get().onDataUpdated(state);

  }

  @Override
  public void onBackPressed() {
    // Log.e(TAG, "onBackPressed()");
    DetailToMasterState estado = new DetailToMasterState();
    estado.data = state.data;
    estado.clicks = state.clicks;
    passStateToPreviousScreen(estado);
  }

  @Override
  public void onPause() {
    // Log.e(TAG, "onPause()");
  }

  @Override
  public void onDestroy() {
    // Log.e(TAG, "onDestroy()");
  }

  @Override
  public void onButtonPressed() {
    // Log.e(TAG, "onButtonPressed()");

    model.aumentarCounter(state.data);

    int a = Integer.parseInt(state.clicks);
    a++;
    state.clicks = a + "";


    onResume();
  }

  private void passStateToPreviousScreen(DetailToMasterState state) {
    mediator.setPreviousDetailScreenState(state);
  }

  private MasterToDetailState getStateFromPreviousScreen() {
    return mediator.getPreviousDetailScreenState();
  }

  @Override
  public void injectView(WeakReference<DetailContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(DetailContract.Model model) {
    this.model = model;
  }


}
