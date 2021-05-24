package es.ulpgc.eite.cleancode.clickcounter.master;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.cleancode.clickcounter.data.CounterData;

public class MasterViewModel {

  // put the view state here
  public List<CounterData> datasource = new ArrayList<>();
  public Long id;
  public String clicks;

}
