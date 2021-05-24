package es.ulpgc.eite.cleancode.clickcounter.detail;

public class DetailModel implements DetailContract.Model {

  public static String TAG = DetailModel.class.getSimpleName();

  private String data;

  @Override
  public String getStoredData() {
    // Log.e(TAG, "getStoredData()");
    return data;
  }

  @Override
  public void onRestartScreen(String data) {
    // Log.e(TAG, "onRestartScreen()");
    this.data = data;
  }

  @Override
  public void onDataFromNextScreen(String data) {
    // Log.e(TAG, "onDataFromNextScreen()");
  }

  @Override
  public void onDataFromPreviousScreen(String data) {
    // Log.e(TAG, "onDataFromPreviousScreen()");
    this.data = data;
  }

  @Override
  public void inicializar() {
    this.data = "1";
  }

  @Override
  public void aumentarCounter(String counter) {
    int a = Integer.parseInt(counter);
    a ++;
    this.data = a + "";
  }
}
