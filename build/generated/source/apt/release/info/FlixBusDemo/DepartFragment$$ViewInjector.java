// Generated code from Butter Knife. Do not modify!
package info.FlixBusDemo;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class DepartFragment$$ViewInjector {
  public static void inject(Finder finder, final info.FlixBusDemo.DepartFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361801, "field 'saveListView'");
    target.saveListView = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131361795, "field 'currentTimeTV'");
    target.currentTimeTV = (android.widget.TextView) view;
  }

  public static void reset(info.FlixBusDemo.DepartFragment target) {
    target.saveListView = null;
    target.currentTimeTV = null;
  }
}
