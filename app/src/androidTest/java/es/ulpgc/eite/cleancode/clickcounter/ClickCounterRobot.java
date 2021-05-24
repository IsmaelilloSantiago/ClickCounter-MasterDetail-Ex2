package es.ulpgc.eite.cleancode.clickcounter;

import android.view.View;
import android.widget.ListView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import es.ulpgc.eite.cleancode.clickcounter.data.CounterData;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class ClickCounterRobot {

    private static Matcher<View> withListSize(final int size) {

        return new TypeSafeMatcher<View>() {

            @Override public boolean matchesSafely (final View view) {
                return ((ListView) view).getCount() == size;
            }

            @Override public void describeTo (final Description description) {
                description.appendText("ListView should have " + size + " items");
            }
        };
    }

    private static Matcher<View> withValueAtPosition(final int val, final int pos) {

        return new TypeSafeMatcher<View>() {

            @Override public boolean matchesSafely (final View view) {
                int size = ((ListView) view).getCount();
                if(pos >= size) {
                    return false;
                }

                Object obj = ((ListView) view).getItemAtPosition(pos);
                CounterData item = (CounterData) obj;
                return item.value == val;
            }

            @Override public void describeTo (final Description description) {
                description.appendText(
                    "ListView should have " + val + " item at position " + pos
                );
            }
        };
    }


    public void mostrarListaConNumeroDeContadoresP1(String p1) {

        int size = Integer.valueOf(p1);

        onView (withId (R.id.list)).check (matches (withListSize (size)));
    }

    public void pulsarBotonMaster() {
        onView(withId(R.id.master)).perform(click());
    }

    public void mostrarContadorEnListaEnPosicionP1ConValorP2(String p1, String p2) {

        int pos = Integer.valueOf(p1);
        int val = Integer.valueOf(p2);

        onView(withId (R.id.list))
            .check(matches(withValueAtPosition(val, pos)));

    }

    public void mostrarContadorConValorP1(String p1) {
        onView(withId(R.id.counter))
            .check(matches(isDisplayed()))
            .check(matches(withText(p1)));
    }

    public void mostrarNumeroDeClicksConValorP1(String p1) {
        onView(withId(R.id.clicks))
            .check(matches(isDisplayed()))
            .check(matches(withText(p1)));
    }

    public void pulsarBotonContadorEnPosicionP1(String p1) {
        int pos = Integer.valueOf(p1);

        onData(allOf())
            .inAdapterView(withId(R.id.list))
            .atPosition(pos)
            .perform(click());
    }


    public void pulsarBotonDetalle() {
        onView(withId(R.id.detail)).perform(click());
    }

    public void pulsarBotonRegresar() {
        pressBack();
    }

    public void pulsarBotonMasterNumeroDeVecesP1(String p1) {
        int length = Integer.valueOf(p1);

        for(int index=0; index < length; index++){
            pulsarBotonMaster();
        }
    }

    public void pulsarBotonContadorEnPosicionP1NumeroDeVecesP2(String p1, String p2) {
        int length = Integer.valueOf(p2);

        for(int index=0; index < length; index++){
            pulsarBotonContadorEnPosicionP1(p1);
            pulsarBotonRegresar();
        }
    }

    public void pulsarBotonDetalleNumeroDeVecesP1(String p1) {
        int length = Integer.valueOf(p1);

        for(int index=0; index < length; index++){
            pulsarBotonDetalle();
        }
    }
}