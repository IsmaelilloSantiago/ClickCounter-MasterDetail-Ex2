package es.ulpgc.eite.cleancode.clickcounter.master;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.clickcounter.app.AppMediator;
import es.ulpgc.eite.cleancode.clickcounter.app.DetailToMasterState;
import es.ulpgc.eite.cleancode.clickcounter.app.MasterToDetailState;
import es.ulpgc.eite.cleancode.clickcounter.data.CounterData;

public class MasterPresenter implements MasterContract.Presenter {

  public static String TAG = MasterPresenter.class.getSimpleName();

  private WeakReference<MasterContract.View> view;
  private MasterState state;
  private MasterContract.Model model;
  private AppMediator mediator;

  public MasterPresenter(AppMediator mediator) {
    this.mediator = mediator;
    state = mediator.getMasterState();
  }


  @Override
  public void onStart() {
    Log.e(TAG, "onStart()");

    // initialize the state if is necessary
    if (state == null) {
      state = new MasterState();
    }
    state.clicks = "0";

  }

  @Override
  public void onRestart() {
    Log.e(TAG, "onRestart()");

    // update the model if is necessary
    model.onRestartScreen(state.datasource);
  }

  @Override
  public void onResume() {
    Log.e(TAG, "onResume()");

    // use passed state if is necessary
    DetailToMasterState savedState = getStateFromNextScreen();

    if (savedState != null) {
      //Log.e(TAG,savedState.data);
      // update the model if is necessary
      state.clicks = savedState.clicks;
      model.onDataFromNextScreen(savedState.data);

      for(int i = 0; i < state.datasource.size(); i ++){
        if(state.datasource.get(i).id == state.id){
          int a = Integer.parseInt(savedState.data);
          state.datasource.get(i).value = a;
        }
      }

    }

    // call the model and update the state
    state.datasource = model.getStoredData();

    // update the view
    view.get().onDataUpdated(state);

  }

  @Override
  public void onBackPressed() {
    // Log.e(TAG, "onBackPressed()");
  }

  @Override
  public void onPause() {
    // Log.e(TAG, "onPause()");
  }

  @Override
  public void onDestroy() {
    // Log.e(TAG, "onDestroy()");
  }

  private void passStateToNextScreen(MasterToDetailState state) {
    mediator.setNextMasterScreenState(state);
  }


  private DetailToMasterState getStateFromNextScreen() {
    return mediator.getNextMasterScreenState();
  }

  @Override
  public void onButtonPressed() {
    Log.e(TAG, "onButtonPressed()");
    model.addElement();
    state.datasource = model.getStoredData();


    view.get().onDataUpdated(state);
  }

  @Override
  public void selectItem(CounterData elemento) {
    state.id = elemento.id;

    model.aumentarCounter(elemento);

    int a = Integer.parseInt(state.clicks);
    a ++;
    state.clicks = a + "";

    MasterToDetailState estado = new MasterToDetailState();
    estado.data = elemento.value + "";
    estado.clicks = state.clicks;
    passStateToNextScreen(estado);
    view.get().navigateToNextScreen();
  }

  @Override
  public void injectView(WeakReference<MasterContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(MasterContract.Model model) {
    this.model = model;
  }

}
