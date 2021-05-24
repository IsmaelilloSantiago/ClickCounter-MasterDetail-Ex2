package es.ulpgc.eite.cleancode.clickcounter.detail;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.clickcounter.app.AppMediator;

public class DetailScreen {

  public static void configure(DetailContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = AppMediator.getInstance();

    DetailContract.Presenter presenter = new DetailPresenter(mediator);
    DetailContract.Model model = new DetailModel();
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
