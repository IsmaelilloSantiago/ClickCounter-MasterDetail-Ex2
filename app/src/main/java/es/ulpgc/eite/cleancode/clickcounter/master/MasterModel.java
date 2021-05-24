package es.ulpgc.eite.cleancode.clickcounter.master;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.clickcounter.data.CounterData;

public class MasterModel implements MasterContract.Model {

  public static String TAG = MasterModel.class.getSimpleName();

  private List<CounterData> datasource;

  public MasterModel() {
    datasource = new ArrayList<>();
  }

  @Override
  public List<CounterData> getStoredData() {
    // Log.e(TAG, "getStoredData()");
    return datasource;
  }

  @Override
  public void onRestartScreen(List<CounterData> datasource) {
    // Log.e(TAG, "onRestartScreen()");
    this.datasource = datasource;
  }

  @Override
  public void onDataFromNextScreen(String data) {
    // Log.e(TAG, "onDataFromNextScreen()");

  }

  @Override
  public void onDataFromPreviousScreen(String data) {
    // Log.e(TAG, "onDataFromPreviousScreen()");
  }

  @Override
  public void addElement() {
    datasource.add(new CounterData());

  }
  @Override
  public void aumentarCounter(CounterData elemento) {
    elemento.value++;
  }
}
