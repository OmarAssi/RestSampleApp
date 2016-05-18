// Generated code from Butter Knife. Do not modify!
package info.FlixBusDemo;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CustomBusAdapter$$ViewInjector {
  public static void inject(Finder finder, final info.FlixBusDemo.CustomBusAdapter target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361804, "field 'destinationTv'");
    target.destinationTv = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131361803, "field 'routeTv'");
    target.routeTv = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131361802, "field 'directionTv'");
    target.directionTv = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131361805, "field 'timingTv'");
    target.timingTv = (android.widget.TextView) view;
  }

  public static void reset(info.FlixBusDemo.CustomBusAdapter target) {
    target.destinationTv = null;
    target.routeTv = null;
    target.directionTv = null;
    target.timingTv = null;
  }
}
